package org.search.elastic.steka.openapi.client;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.search.elastic.steka.openapi.domain.dto.request.MovieSearchRequestDTO;
import org.search.elastic.steka.openapi.domain.dto.response.MovieListResultWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * packageName    : org.search.elastic.steka.openapi.service
 * fileName       : MovieOpenApiService
 * author         : ipeac
 * date           : 24. 4. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 30.        ipeac       최초 생성
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MovieOpenClient {
    private final WebClient webclient;
    
    public Flux<MovieListResultWrapper> getMovies(@Valid MovieSearchRequestDTO movieSearchRequestDTO) {
        return webclient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/searchMovieList.json")
                        .queryParam("curPage", movieSearchRequestDTO.getCurPage())
                        .queryParam("itemPerPage", movieSearchRequestDTO.getItemPerPage())
                        .queryParam("movieNm", movieSearchRequestDTO.getMovieNm())
                        .queryParam("directorNm", movieSearchRequestDTO.getDirectorNm())
                        .queryParam("openStartDt", movieSearchRequestDTO.getOpenStartDt())
                        .queryParam("openEndDt", movieSearchRequestDTO.getOpenEndDt())
                        .queryParam("prdtStartYear", movieSearchRequestDTO.getPrdtStartYear())
                        .queryParam("prdtEndYear", movieSearchRequestDTO.getPrdtEndYear())
                        .queryParam("repNationCd", movieSearchRequestDTO.getRepNationCd())
                        .queryParam("movieTypeCd", movieSearchRequestDTO.getMovieTypeCd())
                        .build()
                )
                .retrieve()
                .bodyToFlux(MovieListResultWrapper.class)
                .doOnComplete(() -> log.info("complete"))
                .doOnError(throwable -> {
                    log.error("error : {}", throwable.getMessage());
                })
                .onErrorResume(throwable -> {
                    log.error("Error retrieving movies from OpenAPI : {}", throwable.getMessage());
                    return Flux.empty();
                });
    }
}