package s1014ftjavaangular.userservice.domain.model.dto.response;

import lombok.Builder;

@Builder
public record ExceptionDTO(
        String detail,
        String type,
        Integer status
) {
}
