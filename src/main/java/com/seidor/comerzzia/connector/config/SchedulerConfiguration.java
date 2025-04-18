package com.seidor.comerzzia.connector.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;

@Configuration
public class SchedulerConfiguration {
	
	@Bean
	public LockProvider lockProvider(DataSource dataSource) {
		
		return new JdbcTemplateLockProvider(dataSource);
		
	}
}
