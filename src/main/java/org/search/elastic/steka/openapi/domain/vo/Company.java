package org.search.elastic.steka.openapi.domain.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * packageName    : org.search.elastic.steka.openapi.domain.collection
 * fileName       : Company
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
public class Company {
    @Field(type = FieldType.Keyword)
    private String companyCd;
    
    @Field(type = FieldType.Text)
    private String companyNm;
    
    @Builder
    private Company(String companyCd, String companyNm) {
        this.companyCd = companyCd;
        this.companyNm = companyNm;
    }
}