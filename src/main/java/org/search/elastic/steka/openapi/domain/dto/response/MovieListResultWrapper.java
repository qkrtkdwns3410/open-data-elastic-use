package org.search.elastic.steka.openapi.domain.dto.response;

import lombok.Builder;

/**
 * packageName    : org.search.elastic.steka.openapi.domain.dto.response
 * fileName       : MovieListResultWrapper
 * author         : ipeac
 * date           : 24. 5. 1.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 1.        ipeac       최초 생성
 */
public record MovieListResultWrapper(
        MovieSearchResponseDTO movieListResult
) {
    @Builder
    public MovieListResultWrapper {
    }
}