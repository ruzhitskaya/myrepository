package by.test.newidea.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ConnectionPoolConfig {


    //Statement
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    //Prepared Statement
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    //Connection Pool
    @Bean
    public DataSource hikariDatasource(DatabaseProperties databaseConfig) {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setJdbcUrl(databaseConfig.getUrl());
        hikariDataSource.setUsername(databaseConfig.getLogin());
        hikariDataSource.setPassword(databaseConfig.getPassword());
        hikariDataSource.setDriverClassName(databaseConfig.getDriverName());
        hikariDataSource.setMaximumPoolSize(10);

        return hikariDataSource;
    }
}
