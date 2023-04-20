package za.co.bmw.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    @Bean
    public void configureFlyway(){
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:sqlite/store.sqlite", "", "")
                .locations("classpath:db/migration")
                .load();

        flyway.migrate();
    }
}
