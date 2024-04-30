package org.search.elastic.steka.openapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * packageName    : org.search.elastic.steka.openapi.config
 * fileName       : WebClientConfig
 * author         : ipeac
 * date           : 24. 4. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 30.        ipeac       최초 생성
 */
@Configuration
public class WebClientConfig {
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}