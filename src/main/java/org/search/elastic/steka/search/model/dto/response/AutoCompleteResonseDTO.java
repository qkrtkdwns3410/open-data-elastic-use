package org.search.elastic.steka.search.model.dto.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link org.search.elastic.steka.openapi.domain.document.Movie}
 */
public record AutoCompleteResonseDTO(@NotNull @NotEmpty String movieNm, @NotNull @NotEmpty String movieNmEn) implements Serializable {
    @Builder
    public AutoCompleteResonseDTO {
    }
}