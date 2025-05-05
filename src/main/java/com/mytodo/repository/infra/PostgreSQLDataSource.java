package com.mytodo.repository.infra;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.env.Environment;

public class PostgreSQLDataSource {
	
	@Autowired
	private Environment environment;
	
	public DataSource dataSource() {
		return DataSourceBuilder.create()
			.driverClassName("org.postgresql.Driver")
            .url(environment.getProperty("spring.datasource.url"))
            .username(environment.getProperty("spring.datasource.username"))
            .password(environment.getProperty("spring.datasource.password"))
            .build();
	}
}
