package br.com.onboard.schoolquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SchoolqueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolqueryApplication.class, args);
    }

}
