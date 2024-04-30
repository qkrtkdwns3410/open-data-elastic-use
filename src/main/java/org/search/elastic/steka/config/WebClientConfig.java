package org.search.elastic.steka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

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
    
    @Value("${movie.openapi.service-key}")
    private String serviceKey;
    
    @Bean
    public WebClient movieClient() {
        return WebClient.builder()
                .baseUrl("http://www.kobis.or.kr/kobisopenapi/webservice/rest")
                .filter(addServiceKey("key", serviceKey))
                .build();
    }
    
    private ExchangeFilterFunction addServiceKey(String param, String value) {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            String originalUrl = clientRequest.url().toString();
            String newUrl = originalUrl + (originalUrl.contains("?") ? "&" : "?") + param + "=" + value;
            
            return clientRequest.url(URI.create(newUrl)).build();
        });
    }
}