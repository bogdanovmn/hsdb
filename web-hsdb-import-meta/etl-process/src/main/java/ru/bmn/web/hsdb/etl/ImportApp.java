package ru.bmn.web.hsdb.etl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.bmn.web.hsdb.model.entity.EntityFactory;
import ru.bmn.web.hsdb.model.entity.EntityMapFactory;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.bmn.web.hsdb.model.repository")
@EntityScan(basePackages = "ru.bmn.web.hsdb.model.entity")
public class ImportApp {
	public static void main(String[] args)
		throws IOException
	{
		SpringApplication.run(ImportApp.class, args)
			.getBean(ImportService.class)
				.run();
	}

	@Bean
	public ImportService getImportService()
		throws Exception
	{
		String importType = System.getProperty("importType", "");

		ImportService result;

		switch (importType) {
			case "collection":
				result = new HearthpwnCollectionImport();
				break;
			case "database":
				result = new HearthpwnDatabaseImport();
				break;
			default:
				throw new Exception("wrong importType value");
		}

		return result;
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
