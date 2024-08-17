package s1014ftjavaangular.loan.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class InterestRateLoan {
    private String loanId;
    private String name;
    private Double annualPercentage;
    private LocalDate expiredDate;
}
