package com.library.publications;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("com.library.publications.repository")
@SpringBootApplication(scanBasePackages = {"com.library.publications"}, exclude = JpaRepositoriesAutoConfiguration.class)
public class PublicationApp implements CommandLineRunner{


    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(PublicationApp.class);
        application.run(args);
    }

    @Override
    public void run(String... strings) throws Exception {

        System.out.println("Hello Miami");
    }
}