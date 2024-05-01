package org.search.elastic.steka.openapi.domain.dto.response;

import lombok.Builder;
import org.search.elastic.steka.openapi.domain.document.Movie;

import java.util.List;
import java.util.stream.Collectors;

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
    
    public Movie toDocument() {
        return Movie.builder()
                .movieCd(movieCd)
                .movieNm(movieNm)
                .movieNmEn(movieNmEn)
                .prdtYear(prdtYear)
                .openDt(openDt)
                .typeNm(typeNm)
                .prdtStatNm(prdtStatNm)
                .nationAlt(nationAlt)
                .genreAlt(genreAlt)
                .repNationNm(repNationNm)
                .repGenreNm(repGenreNm)
                .directors(directors.stream().map(DirectorDTO::toDocument).collect(Collectors.toList()))
                .companys(companys.stream().map(CompanyDTO::toDocument).collect(Collectors.toList()))
                .build();
    }
}