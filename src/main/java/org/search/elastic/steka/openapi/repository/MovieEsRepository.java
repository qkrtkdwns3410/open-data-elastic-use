package org.search.elastic.steka.openapi.repository;

import org.search.elastic.steka.openapi.domain.document.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;
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
    @Query("""
            {
              "bool": {
                "should": [
                  { "prefix": { "movieNm": "?0" }},
                  { "prefix": { "movieNmEn": "?0" }}
                ],
                "minimum_should_match": 1
              }
            }
            """)
    Flux<Movie> findByMovieNmStartingWithOrMovieNmEnStartingWith(String startMovieName, String query, Pageable pageable);
}