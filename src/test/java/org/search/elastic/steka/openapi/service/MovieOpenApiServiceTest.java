package org.search.elastic.steka.openapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.search.elastic.steka.openapi.client.MovieOpenClient;
import org.search.elastic.steka.openapi.domain.dto.request.MovieSearchRequestDTO;
import org.search.elastic.steka.openapi.domain.dto.response.MovieSearchResponseDTO;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MovieOpenApiServiceTest {
    
    @Mock
    private MovieOpenClient movieOpenClient;
    
    @InjectMocks
    private MovieService movieService;
    
    private MovieSearchRequestDTO movieSearchRequestDTO;
    private MovieSearchResponseDTO movieSearchResponseDTO;
    
    @BeforeEach
    public void setUp() {
        movieSearchRequestDTO = new MovieSearchRequestDTO("1", "10", "movie", "director", "2020", "2021", "2020", "2021", "2204", "2201");
        movieSearchResponseDTO = new MovieSearchResponseDTO(1, "source", List.of());
    }
    
    @Test
    @DisplayName("영화 OpenAPI에서 영화 목록을 요청하면 올바른 요청이면 MovieSearchResponseDTO의 Flux를 반환한다.")
    public void testGetMoviesFromOpenApiWhenRequestIsValidThenReturnFluxOfMovieSearchResponseDTO() {
        when(movieOpenClient.getMovies(movieSearchRequestDTO)).thenReturn(Flux.just(movieSearchResponseDTO));
        
        Flux<MovieSearchResponseDTO> result = movieService.getMoviesFromOpenApi(movieSearchRequestDTO);
        
        StepVerifier.create(result)
                .expectNext(movieSearchResponseDTO)
                .verifyComplete();
        
        verify(movieOpenClient, times(1)).getMovies(movieSearchRequestDTO);
    }
}