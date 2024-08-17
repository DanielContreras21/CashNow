package s1014ftjavaangular.userservice.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s1014ftjavaangular.userservice.domain.model.enums.HousingStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResidenceDetails {
    private String id;
    private String city;
    private String state;
    private String address;
    private String apartment;
    private String zipCode;
}
