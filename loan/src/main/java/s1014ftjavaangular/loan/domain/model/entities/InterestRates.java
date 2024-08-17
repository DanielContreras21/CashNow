package s1014ftjavaangular.loan.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class InterestRates {
    private String interestRatesUuid;
    private String name;
    private Double annualPercentage;
    private Boolean status;
}
