package org.search.elastic.steka.openapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.search.elastic.steka.openapi.domain.dto.request.MovieSearchRequestDTO;
import org.search.elastic.steka.openapi.domain.dto.response.MovieResponseDTO;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

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
    
    public void synchronizeMoviesToEs(MovieSearchRequestDTO requestDTO) {
        log.info("synchronizeMoviesToEs");
        
        movieService.getMoviesFromOpenApi(requestDTO)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(movieListResultWrapper -> {
                    movieService.saveMoviesBulkToEs(movieListResultWrapper.movieListResult().movieList().stream()
                            .map(MovieResponseDTO::toDocument)
                            .toList());
                })
                .subscribe();
    }
}