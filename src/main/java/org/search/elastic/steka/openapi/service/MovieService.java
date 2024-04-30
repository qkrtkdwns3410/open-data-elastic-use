package org.search.elastic.steka.openapi.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.search.elastic.steka.openapi.client.MovieOpenClient;
import org.search.elastic.steka.openapi.domain.dto.request.MovieSearchRequestDTO;
import org.search.elastic.steka.openapi.domain.dto.response.MovieSearchResponseDTO;
import org.search.elastic.steka.openapi.repository.MovieEsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MovieService {
    private final MovieOpenClient movieOpenClient;
    private final MovieEsRepository movieEsRepository;
    
    public Flux<MovieSearchResponseDTO> getMovies(@Valid MovieSearchRequestDTO movieSearchRequestDTO) {
        return movieOpenClient.getMovies(movieSearchRequestDTO);
    }
    
    //호출된 값을 es 에 저장하기
    public void saveMovies() {
        movieEsRepository.saveAll();
    }
    
}