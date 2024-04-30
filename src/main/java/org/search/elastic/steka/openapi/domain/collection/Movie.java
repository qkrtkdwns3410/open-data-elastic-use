package org.search.elastic.steka.openapi.domain.collection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * packageName    : org.search.elastic.steka.openapi.domain.collection
 * fileName       : MovieCollection
 * author         : ipeac
 * date           : 24. 5. 1.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 1.        ipeac       최초 생성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "MOVIE")
public class Movie {
    @Id
    @Field(type = FieldType.Keyword)
    private String movieCd;
    
    @Field(type = FieldType.Text)
    private String movieNm;
    
    @Field(type = FieldType.Text)
    private String movieNmEn;
    
    @Field(type = FieldType.Date)
    private String prdtYear;
    
    @Field(type = FieldType.Keyword)
    private String openDt;
    
    @Field(type = FieldType.Keyword)
    private String typeNm;
    
    @Field(type = FieldType.Keyword)
    private String prdtStatNm;
    
    @Field(type = FieldType.Keyword)
    private String nationAlt;
    
    @Field(type = FieldType.Keyword)
    private String genreAlt;
    
    @Field(type = FieldType.Keyword)
    private String repNationNm;
    
    @Field(type = FieldType.Keyword)
    private String repGenreNm;
    
    @Field(type = FieldType.Nested)
    private List<Director> directors;
    
    @Field(type = FieldType.Nested)
    private List<Company> companys;
}