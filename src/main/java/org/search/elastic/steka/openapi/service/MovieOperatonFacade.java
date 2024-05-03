package org.search.elastic.steka.openapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.search.elastic.steka.openapi.domain.dto.request.MovieSearchRequestDTO;
import org.search.elastic.steka.openapi.domain.dto.response.MovieResponseDTO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Objects;

/**
 * packageName    : org.search.elastic.steka.openapi.service
 * fileName       : MovieService
 * author         : ipeac
 * date           : 24. 5. 1.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 1.        ipeac       최초 생성
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MovieOperatonFacade {
    private final MovieService movieService;
    
    public void synchronizeMoviesToEs() {
        log.info("Synchronizing movies to ES...");
        
        MovieSearchRequestDTO requestDTO = MovieSearchRequestDTO.of("1", "100");
        
        movieService.getMoviesFromOpenApi(requestDTO)
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(movieListResultWrapper -> {
                    int totCnt = movieListResultWrapper.movieListResult().totCnt();
                    int totalPages = (int) Math.ceil((double) totCnt / 100); // 여기서 100은 한 페이지당 결과 수입니다.
                    
                    log.info("Total pages to be fetched: {}", totalPages);
                    
                    return Flux.range(1, totalPages)
                            .flatMap(page -> {
                                requestDTO.setCurPage(String.valueOf(page));
                                log.info("Requesting movies from OpenAPI for page: {}", page);
                                
                                return movieService.getMoviesFromOpenApi(requestDTO)
                                        .subscribeOn(Schedulers.boundedElastic())
                                        .flatMap(movieListResult -> {
                                            if (Objects.isNull(movieListResult.movieListResult()))
                                                return Flux.empty();
                                            
                                            return Flux.fromIterable(movieListResult.movieListResult().movieList());
                                        })
                                        .map(MovieResponseDTO::toDocument)
                                        .collectList()
                                        .flatMapMany(movieService::saveMoviesBulkToEs);
                            });
                    
                    
                })
                .subscribe(
                        result -> {},
                        error -> log.error("Failed to synchronize movies to ES: ", error),
                        () -> log.info("Synchronization of movies to ES is completed.")
                );
    }
}