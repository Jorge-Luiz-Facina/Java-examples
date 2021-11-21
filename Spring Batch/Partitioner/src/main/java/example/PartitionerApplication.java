package example;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class PartitionerApplication implements CommandLineRunner
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PartitionerApplication.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    public static void main(String[] args) {
        SpringApplication.run(PartitionerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        LOGGER.info("STATUS STARTED");

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobId", String.valueOf(System.currentTimeMillis()))
                .addDate("date", new Date())
                .addLong("time",System.currentTimeMillis()).toJobParameters();

        JobExecution execution = jobLauncher.run(job, jobParameters);

        LOGGER.info("STATUS :: "+execution.getStatus());
        LOGGER.info("EXIT STATUS :: "+execution.getExitStatus());
    }
}
