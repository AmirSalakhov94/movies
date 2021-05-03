package tech.itpark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;

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
