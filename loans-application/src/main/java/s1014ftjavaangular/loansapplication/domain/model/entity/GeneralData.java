package s1014ftjavaangular.loansapplication.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s1014ftjavaangular.loansapplication.domain.model.enums.IdentificationType;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GeneralData {

    private String loanApplicationId;
    private String identification;
    private IdentificationType identificationType;
    private String name;
    private String lastname;
    private String genre;
    private LocalDate birthdate;
    private String nationality;
    private String city;
    private String state;
    private String address;
    private String apartment;
    private String zipcode;
    private String phone;
}
