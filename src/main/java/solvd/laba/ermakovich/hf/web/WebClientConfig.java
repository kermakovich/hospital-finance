package solvd.laba.ermakovich.hf.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Ermakovich Kseniya
 */
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClient() {
        return WebClient.builder();
    }
}
