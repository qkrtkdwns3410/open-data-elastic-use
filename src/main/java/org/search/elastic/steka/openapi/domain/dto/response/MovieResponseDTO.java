package org.search.elastic.steka.openapi.domain.dto.response;

import lombok.Builder;

import java.util.List;

/**
 * packageName    : org.search.elastic.steka.openapi.domain.dto.response
 * fileName       : MovieResponseDTO
 * author         : ipeac
 * date           : 24. 4. 30.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 30.        ipeac       최초 생성
 */
public record MovieResponseDTO(
        String movieCd,
        String movieNm,
        String movieNmEn,
        String prdtYear,
        String openDt,
        String typeNm,
        String prdtStatNm,
        String nationAlt,
        String genreAlt,
        String repNationNm,
        String repGenreNm,
        List<DirectorDTO> directors,
        List<CompanyDTO> companys
) {
    
    @Builder
    public MovieResponseDTO {
    }
}