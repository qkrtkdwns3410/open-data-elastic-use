package org.search.elastic.steka.search.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.search.elastic.steka.openapi.domain.document.Movie;
import org.search.elastic.steka.openapi.repository.MovieEsRepository;
import org.search.elastic.steka.search.model.dto.response.AutoCompleteResonseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.elasticsearch.DataElasticsearchTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

/**
 * packageName    : org.search.elastic.steka.search.service
 * fileName       : SearchServiceTest
 * author         : ipeac
 * date           : 24. 5. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 12.        ipeac       최초 생성
 */
@ActiveProfiles("test")
@DataElasticsearchTest
class SearchServiceTest {
    
    private SearchService searchService;
    
    @Autowired
    private MovieEsRepository movieEsRepository;
    
    @BeforeEach
    void setUp() {
        searchService = new SearchService(movieEsRepository);
    }
    
    @AfterEach
    void tearDown() {
        movieEsRepository.deleteAll().block();
    }
    
    @Test
    @DisplayName("es 첫번쨰 단어에 해당하는 데이터만 들고오는지 테스트")
    void When_Query_First_Word_Then_Return_Movie() {
        //given
        Movie 조회되어야함 = Movie.builder()
                .movieNm("테스트 영화")
                .build();
        
        Movie 조회되면안됨 = Movie.builder()
                .movieNm("영화 테스트")
                .build();
        
        movieEsRepository.saveAll(List.of(조회되어야함, 조회되면안됨)).blockLast();
        
        //when
        Flux<AutoCompleteResonseDTO> actual = searchService.search("테스트");
        
        //then
        StepVerifier.create(actual)
                .expectNextMatches(dto -> {
                    return dto.movieNm().startsWith("테스트");
                })
                .expectComplete()
                .verify();
    }
}