package example.configuration;

import javax.sql.DataSource;

import example.component.GameJobCompletionNotificationListener;
import example.model.Game;
import example.processor.GameItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${file.input}")
    private String fileInput;

    @Bean
    public FlatFileItemReader<Game> reader() {
        return new FlatFileItemReaderBuilder<Game>().name("GameItemReader")
                .resource(new ClassPathResource(fileInput))
                .delimited()
                .names(new String[] { "name", "developer", "genre" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Game>() {{
                    setTargetType(Game.class);
                }})
                .build();
    }

    @Bean
    public GameItemProcessor processor() {
        return new GameItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Game> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Game>().itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO game (name, developer, genre) VALUES (:name, :developer, :genre)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importGamerJob(GameJobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importGamerJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Game> writer) {
        return stepBuilderFactory.get("step1")
                .<Game, Game> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}