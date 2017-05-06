package ru.bmn.web.hsdb.etl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.bmn.web.hsdb.model.repository")
@EntityScan(basePackages = "ru.bmn.web.hsdb.model.entity")
public class Import {
	public static void main(String[] args)
		throws IOException
	{
		SpringApplication.run(Import.class, args)
			.getBean(HearthpwnDatabaseImport.class)
				.run();
	}

	@Bean
	public HearthpwnDatabaseImport getHearthpwnDatabaseImport() {
		return new HearthpwnDatabaseImport();
	}

	@Bean
	public EntityFactory getEntityFactory() {
		return new EntityFactory();
	}

}
