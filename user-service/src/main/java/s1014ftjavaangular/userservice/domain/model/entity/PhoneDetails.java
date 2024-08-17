package s1014ftjavaangular.userservice.domain.model.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s1014ftjavaangular.userservice.domain.model.enums.PhoneLabel;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDetails {

    @NotEmpty
    private PhoneLabel phoneLabel;
    @NotEmpty
    private String countryCode;
    @NotEmpty
    private String cityCode;
    @NotEmpty
    private String phoneNumber;
}
