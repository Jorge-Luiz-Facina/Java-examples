package example.component;

import example.model.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class GameJobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameJobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameJobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("!!! JOB FINISHED! Time to verify the results");

            String query = "SELECT id, name, developer, genre FROM new_game";
            jdbcTemplate.query(query, (rs, row) -> new Game(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4)))
                    .forEach(game -> LOGGER.info("Found < {} > in the database.", game));
        }
    }
}
