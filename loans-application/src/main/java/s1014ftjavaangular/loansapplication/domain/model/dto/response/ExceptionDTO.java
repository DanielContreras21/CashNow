package s1014ftjavaangular.loansapplication.domain.model.dto.response;

import lombok.Builder;

@Builder
public record ExceptionDTO(
        String detail,
        String type,
        Integer status
) {
}
