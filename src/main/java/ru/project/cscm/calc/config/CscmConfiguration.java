package ru.project.cscm.calc.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application_orm.properties" })
@EnableJpaRepositories("ru.project.cscm.calc.base")
@ComponentScan
public class CscmConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public DataSource ormDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("hibernate.datasource.driver-class-name"));
		dataSource.setDefaultAutoCommit(false);
		dataSource.setUsername(env.getProperty("hibernate.datasource.username"));
		dataSource.setPassword(env.getProperty("hibernate.datasource.password"));
		dataSource.setUrl(env.getProperty("hibernate.datasource.url"));

		return dataSource;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("ru.project.cscm.calc.base.model");
		factory.setDataSource(ormDataSource());
		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		factory.setJpaProperties(additionalProperties);
		factory.afterPropertiesSet();

		return factory.getObject();
	}

}
