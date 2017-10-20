package com.github.bogdanovmn.hsdb.webapp;

import com.github.bogdanovmn.hsdb.model.EntityFactory;
import com.github.bogdanovmn.hsdb.model.EntityMapFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.github.bogdanovmn.hsdb.model")
@EntityScan(basePackages = "com.github.bogdanovmn.hsdb.model")
public class App {
	public final static String HOME_PAGE = "/collection/in";

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public EntityFactory getEntityFactory() {
		return new EntityFactory();
	}

	@Bean(initMethod = "init")
	public EntityMapFactory getEntityMapFactory() {
		return new EntityMapFactory();
	}
}

