package s1014ftjavaangular.userservice.domain.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResidenceDetailsDto {
    private String city;
    private String state;
    private String address;
    private String apartment;
    private String zipCode;
}
