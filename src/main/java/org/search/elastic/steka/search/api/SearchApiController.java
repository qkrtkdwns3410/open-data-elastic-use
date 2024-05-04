package org.search.elastic.steka.search.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.search.elastic.steka.search.model.dto.response.AutoCompleteResonseDTO;
import org.search.elastic.steka.search.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * packageName    : org.search.elastic.steka.search.api
 * fileName       : SearchApiController
 * author         : ipeac
 * date           : 24. 5. 4.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 4.        ipeac       최초 생성
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class SearchApiController {
    private final SearchService searchService;
    
    /**
     * 자동완성 문구 추천
     * <p>
     * 별도로 SQL 인젝션 등의 위험이 있을 수 있는데 여기서는 제외하겠다.
     *
     * @param query
     * @return
     */
    @GetMapping("/search")
    public Flux<AutoCompleteResonseDTO> search(@RequestParam("query") String query) {
        log.info("search");
        
        return searchService.search(query);
    }
}