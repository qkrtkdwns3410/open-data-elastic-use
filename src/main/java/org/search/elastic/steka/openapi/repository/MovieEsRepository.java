package org.search.elastic.steka.openapi.repository;

import org.search.elastic.steka.openapi.domain.document.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * packageName    : org.search.elastic.steka.openapi.repository
 * fileName       : MovieEsRepository
 * author         : ipeac
 * date           : 24. 5. 1.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 1.        ipeac       최초 생성
 */
@Repository
public interface MovieEsRepository extends ReactiveElasticsearchRepository<Movie, String> {
    @Query("{\"regexp\":{\"movieNm\":\"?0.*\"}}")
    Flux<Movie> findByMovieNmStartingWith(String startMovieName, Pageable pageable);
}