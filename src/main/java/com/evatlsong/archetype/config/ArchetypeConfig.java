package com.evatlsong.archetype.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.evatlsong.archetype")
public class ArchetypeConfig implements TransactionManagementConfigurer {

  @Bean
  public DataSource dataSource() {
    EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
    edb.setType(EmbeddedDatabaseType.H2);
    EmbeddedDatabase embeddedDatabase = edb.build();
    return embeddedDatabase;
  }

  public PlatformTransactionManager annotationDrivenTransactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactoryBean());
    return transactionManager;
  }

  @Bean
  public SessionFactory sessionFactoryBean() {
    try {
      LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
      lsfb.setDataSource(dataSource());
      lsfb.setPackagesToScan("com.evatlsong.archetype.model");
      Properties props = new Properties();
      props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
      props.setProperty("hibernate.hbm2ddl.auto", "create");
      lsfb.setHibernateProperties(props);
      lsfb.afterPropertiesSet();
      SessionFactory object = lsfb.getObject();
      return object;
    } catch (IOException e) {
      return null;
    }
  }
}
