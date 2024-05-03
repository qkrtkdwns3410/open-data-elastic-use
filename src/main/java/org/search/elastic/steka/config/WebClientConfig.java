package org.search.elastic.steka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
@Slf4j
public class WebClientConfig {
    
    @Value("${movie.api.service-key}")
    private String serviceKey;
    
    @Bean
    public WebClient movieClient() {
        return WebClient.builder()
                .baseUrl("http://www.kobis.or.kr/kobisopenapi/webservice/rest")
                .filter(addQueryString("key", serviceKey))
                .build();
    }
    
    /**
     * 쿼리스트링에 서비스 키를 추가한다.
     *
     * @param param
     * @param value
     * @return
     */
    private ExchangeFilterFunction addQueryString(String param, String value) {
        log.info("addQueryString : param={}, value={}", param, value);
        
        return (clientRequest, nextFilter) -> {
            URI oldUri = clientRequest.url();
            String originalQuery = oldUri.getQuery() != null ? oldUri.getQuery() : "";
            
            // 쿼리 스트링에서 param이 있는지 확인하고, 없으면 추가하거나 있으면 교체
            String newQuery = Stream.of(originalQuery.split("&"))
                    .map(s -> s.split("="))
                    .collect(Collectors.toMap(s -> s[0], s -> s.length > 1 ? s[1] : ""))
                    .entrySet()
                    .stream()
                    .map(entry -> Objects.equals(entry.getKey(), param) ? param + "=" + value : entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&"));
            
            // param이 원래 쿼리에 없었다면 추가
            if (!newQuery.contains(param + "=")) {
                newQuery = newQuery.isEmpty() ? param + "=" + value : newQuery + "&" + param + "=" + value;
            }
            
            URI newUri = UriComponentsBuilder.fromUri(oldUri)
                    .replaceQuery(newQuery)
                    .build(true)
                    .toUri();
            
            ClientRequest filteredRequest = ClientRequest.from(clientRequest)
                    .url(newUri)
                    .build();
            
            return nextFilter.exchange(filteredRequest);
        };
    }
}