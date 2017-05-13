package ru.bmn.web.hsdb.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.bmn.web.hsdb.model.entity.EntityFactory;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.bmn.web.hsdb.model.repository")
@EntityScan(basePackages = "ru.bmn.web.hsdb.model.entity")
public class App {
	public final static String HOME_PAGE = "/collection/in";

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public EntityFactory getEntityFactory() {
		return new EntityFactory();
	}

}

