package org.search.elastic.steka.search.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.search.elastic.steka.openapi.domain.document.Movie;
import org.search.elastic.steka.openapi.repository.MovieEsRepository;
import org.search.elastic.steka.search.model.dto.response.AutoCompleteResonseDTO;
import org.springframework.data.domain.PageRequest;
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

        long start = System.currentTimeMillis();
        Flux<Movie> byMovieNmStartingWith = movieEsRepository.findByMovieNmStartingWithOrMovieNmEnStartingWith(query, query, PageRequest.of(0, 10));
        log.info("search : query={}, elapsed={}", query, System.currentTimeMillis() - start);

        return byMovieNmStartingWith.map(Movie::toAutoCompleteResonseDTO);
    }
}