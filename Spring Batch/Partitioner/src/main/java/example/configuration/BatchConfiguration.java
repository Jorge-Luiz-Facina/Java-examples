package example.configuration;

import example.component.GameJobCompletionNotificationListener;
import example.mapper.GameRowMapper;
import example.model.Game;
import example.partitioner.ColumnRangePartitioner;
import example.processor.GameItemProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class BatchConfiguration
{
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfiguration.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Bean
	public ColumnRangePartitioner partitioner()
	{
		ColumnRangePartitioner columnRangePartitioner = new ColumnRangePartitioner();
		columnRangePartitioner.setColumn("id");
		columnRangePartitioner.setDataSource(dataSource);
		columnRangePartitioner.setTable("game");
		return columnRangePartitioner;
	}

	@Bean
	public GameItemProcessor processor() {
		return new GameItemProcessor();
	}

	@Bean
	@StepScope
	public JdbcPagingItemReader<Game> pagingItemReader(
			@Value("#{stepExecutionContext['minValue']}") Long minValue,
			@Value("#{stepExecutionContext['maxValue']}") Long maxValue) 
	{
		LOGGER.info("reading " + minValue + " to " + maxValue);

		Map<String, Order> sortKeys = new HashMap<>();
		sortKeys.put("id", Order.ASCENDING);
		
		MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
		queryProvider.setSelectClause("id, name, developer, genre");
		queryProvider.setFromClause("from game");
		queryProvider.setWhereClause("where id >= " + minValue + " and id < " + maxValue);
		queryProvider.setSortKeys(sortKeys);
		
		JdbcPagingItemReader<Game> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(this.dataSource);
		reader.setFetchSize(1000);
		reader.setRowMapper(new GameRowMapper());
		reader.setQueryProvider(queryProvider);
		
		return reader;
	}

	@Bean
	@StepScope
	public JdbcBatchItemWriter<Game> writer()
	{
		JdbcBatchItemWriter<Game> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("INSERT INTO NEW_GAME VALUES (:id, :name, :developer, :genre)");

		itemWriter.setItemSqlParameterSourceProvider
			(new BeanPropertyItemSqlParameterSourceProvider<>());
		itemWriter.afterPropertiesSet();
		
		return itemWriter;
	}
	
	@Bean
	public Step step1() 
	{
		return stepBuilderFactory.get("step1")
				.partitioner(slaveStep().getName(), partitioner())
				.step(slaveStep())
				.gridSize(4)
				.taskExecutor(new SimpleAsyncTaskExecutor())
				.build();
	}

	@Bean
	public Step slaveStep() 
	{
		return stepBuilderFactory.get("slaveStep")
				.<Game, Game>chunk(1000)
				.reader(pagingItemReader(null, null))
				.processor(processor())
				.writer(writer())
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
}