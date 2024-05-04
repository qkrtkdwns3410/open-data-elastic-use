package org.search.elastic.steka.search.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.search.elastic.steka.openapi.domain.document.Movie;
import org.search.elastic.steka.openapi.repository.MovieEsRepository;
import org.search.elastic.steka.search.model.dto.response.AutoCompleteResonseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

/**
 * packageName    : org.search.elastic.steka.search.api
 * fileName       : SearchService
 * author         : ipeac
 * date           : 24. 5. 4.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 4.        ipeac       최초 생성
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class SearchService {
    private final MovieEsRepository movieEsRepository;
    
    public Flux<AutoCompleteResonseDTO> search(String query) {
        log.info("search : query={}", query);
        
        Flux<Movie> byMovieNmStartingWith = movieEsRepository.findByMovieNmStartingWith(query);
        
        return byMovieNmStartingWith.map(Movie::toAutoCompleteResonseDTO);
    }
}