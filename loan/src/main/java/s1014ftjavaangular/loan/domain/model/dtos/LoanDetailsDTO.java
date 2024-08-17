package s1014ftjavaangular.loan.domain.model.dtos;

import lombok.Value;

@Value
public class LoanDetailsDTO {
    String calculationType;
    String numberInstallments;
    String frequencyPayment;

}
