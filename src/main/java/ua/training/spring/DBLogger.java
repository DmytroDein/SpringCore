package ua.training.spring;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Dmytro_Deinichenko on 3/20/2017.
 */
public class DBLogger implements EventLogger{

    private JdbcTemplate jdbcTemplate;

    public DBLogger(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void logEvent(Event event) {
        jdbcTemplate.update(
                "INSERT INTO spring_core.logs (log_text) VALUES (?)",
                event.getMsg());
    }
}
