package org.search.elastic.steka.openapi.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

/**
 * packageName    : org.search.elastic.steka.openapi.api
 * fileName       : OpenApiController
 * author         : ipeac
 * date           : 24. 4. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 30.        ipeac       최초 생성
 */
@Slf4j
@RequestMapping("/api/openapi")
@RequiredArgsConstructor
public class OpenApiController {
    
    @GetMapping("/movie")
    public Mono<String> getMovie() {
        return Mono.just("movie");
    }
}