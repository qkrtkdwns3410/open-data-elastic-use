package org.search.elastic.steka.openapi.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.search.elastic.steka.openapi.service.MovieOperatonFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : org.search.elastic.steka.openapi.api
 * fileName       : OpenApiController
 * author         : ipeac
 * date           : 24. 4. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 30.        ipeac       최초 생성
 */
@Slf4j
@RequestMapping("/api/openapi")
@RestController
@RequiredArgsConstructor
public class OpenApiController {
    private final MovieOperatonFacade movieOperatonFacade;
    
    @PostMapping("/batch/movies")
    public void batchAllMovies() {
        movieOperatonFacade.synchronizeMoviesToEs();
    }
}