package net.astercrono.pcsetup.configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean
	@Primary
	public SessionFactory sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		builder.scanPackages("net.astercrono.pcsetup.domain");
		builder.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		builder.setProperty("hibernate.jdbc.lob.non_contextual_creation", "true");
		return builder.buildSessionFactory();
	}
	
	@Bean
	@Primary
	public PlatformTransactionManager platformTransactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}
	
	@Bean
	public FilterRegistrationBean<Filter> registerOpenEntityManager() {
		FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();

		OpenSessionInViewFilter filter = new OpenSessionInViewFilter();
		bean.setFilter(filter);
		bean.setOrder(5);
		
		return bean;
	}
}
