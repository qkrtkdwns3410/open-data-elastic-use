package org.search.elastic.steka.openapi.repository;

import org.search.elastic.steka.openapi.domain.collection.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

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
public interface MovieEsRepository extends ElasticsearchRepository<Movie, String> {

}