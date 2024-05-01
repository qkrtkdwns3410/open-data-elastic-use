package org.search.elastic.steka.openapi.domain.dto.response;

import lombok.Builder;

import java.util.List;

/**
 * packageName    : org.search.elastic.steka.openapi.domain.dto.response
 * fileName       : MovieSearchResponse
 * author         : ipeac
 * date           : 24. 4. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 30.        ipeac       최초 생성
 */
public record MovieSearchResponseDTO(
        int totCnt,
        String Source,
        List<MovieResponseDTO> movieList
) {
    @Builder
    public MovieSearchResponseDTO {
    }
}