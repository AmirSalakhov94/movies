package tech.itpark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.naming.NamingException;
import javax.sql.DataSource;

@EnableAsync
@EnableWebMvc
@Configuration
public class JavaConfiguration {

  @Bean
  public DataSource dataSource() throws NamingException {
    return new JndiTemplate().lookup("java:/comp/env/jdbc/db", DataSource.class);
  }

  @Bean
  public JdbcTemplate jdbcTemplate() throws NamingException {
    return new JdbcTemplate(dataSource());
  }
}
