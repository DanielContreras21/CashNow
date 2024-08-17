package s1014ftjavaangular.loansapplication.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s1014ftjavaangular.loansapplication.domain.model.enums.IdentificationType;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Guarantor {

    private String loanApplicationId;
    private String name;
    private String lastname;
    private IdentificationType identificationType;
    private String identification;
    private String city;
    private String state;
    private String address;
    private String apartment;
    private String zipcode;
    private String phone;
    private String company;
    private String businessCategory;
    private String occupation;
    private Integer timeInCompany;
    private Double monthlyIncome;
    private String companyCity;
    private String companyState;
    private String companyAddress;
    private String companyApartment;
    private String companyZipcode;
    private String companyPhone;
}
