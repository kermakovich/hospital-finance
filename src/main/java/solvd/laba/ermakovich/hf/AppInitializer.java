package solvd.laba.ermakovich.hf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableReactiveMongoRepositories(basePackages = "solvd.laba.ermakovich.hf.repository")
public class AppInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppInitializer.class, args);
    }

}
