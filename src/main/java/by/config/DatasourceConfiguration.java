package by.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

//for JdbcTemplate

@Configuration
@PropertySource("classpath:database.properties")
public class DatasourceConfiguration {

    /*1. Read property file (legacy case)*/
//    @Autowired
//    private Environment properties;

    /*2. Read property (modern case)*/
    @Value("${driverName}")
    private String driverName;

    @Value("${url}")
    private String url;

    @Value("${login}")
    private String login;

    @Value("${password}")
    private String password;

    @Value("${initialSize}")
    private Integer initialSize;

    @Bean
    public DataSource generateDatasource() {

        /*1. Create database config for CP*/
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setDriverClassName(properties.getProperty("driverName"));
//        hikariConfig.setJdbcUrl(properties.getProperty("url"));
//        hikariConfig.setUsername(properties.getProperty("login"));
//        hikariConfig.setPassword(properties.getProperty("password"));
//        hikariConfig.setMaximumPoolSize(Integer.parseInt(Objects.requireNonNull(properties.getProperty("initialSize"))));
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverName);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(login);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(initialSize);

        /*2. Create datasource*/
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
