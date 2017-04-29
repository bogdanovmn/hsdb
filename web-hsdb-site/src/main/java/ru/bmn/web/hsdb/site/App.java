package ru.bmn.web.hsdb.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.bmn.web.hsdb.model.*")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
