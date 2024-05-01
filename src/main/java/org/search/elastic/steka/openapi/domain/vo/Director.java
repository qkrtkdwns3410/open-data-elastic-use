package org.search.elastic.steka.openapi.domain.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * packageName    : org.search.elastic.steka.openapi.domain.collection
 * fileName       : Director
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
public class Director {
    @Field(type = FieldType.Text)
    private String peopleNm;
    
    @Builder
    private Director(String peopleNm) {
        this.peopleNm = peopleNm;
    }
}