package org.search.elastic.steka.openapi.domain.dto.response;

import lombok.Builder;

/**
 * packageName    : org.search.elastic.steka.openapi.domain.dto.response
 * fileName       : DirectorDTO
 * author         : ipeac
 * date           : 24. 4. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 30.        ipeac       최초 생성
 */
public record DirectorDTO(
        String peopleNm
) {
    @Builder
    public DirectorDTO {
    }
}