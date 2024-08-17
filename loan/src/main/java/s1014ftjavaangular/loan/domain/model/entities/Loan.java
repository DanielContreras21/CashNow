package s1014ftjavaangular.loan.domain.model.entities;


import lombok.Builder;
import lombok.Data;
import s1014ftjavaangular.loan.domain.model.enums.AmortizationType;
import s1014ftjavaangular.loan.domain.model.enums.FrequencyPayment;
import s1014ftjavaangular.loan.domain.model.enums.LoanStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Data
@Builder
public class Loan implements Serializable {
    private String loanId;
    private String loanApplicationId;
    private LocalDate createAt;
    private LocalDate startAt;
    private String loanNumber;
    private Double amountApproved;
    private AmortizationType amortizationType;
    private Integer numberInstallments;
    private FrequencyPayment frequencyPayment;
    private LoanStatus status;
    private InterestRateLoan interestRateLoan;
    private LatePaymentRateLoan latePaymentRateLoan;
    private List<AmortizationSchedule> amortizationScheduleList;
}
