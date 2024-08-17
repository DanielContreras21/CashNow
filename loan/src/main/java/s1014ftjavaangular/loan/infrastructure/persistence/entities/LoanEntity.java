package s1014ftjavaangular.loan.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import s1014ftjavaangular.loan.domain.model.entities.Loan;
import s1014ftjavaangular.loan.domain.model.enums.AmortizationType;
import s1014ftjavaangular.loan.domain.model.enums.FrequencyPayment;
import s1014ftjavaangular.loan.domain.model.enums.LoanStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "loan",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_loan_number", columnNames = "loan_number"),
                @UniqueConstraint(name = "uk_application_loan_id", columnNames = "loan_application_id")
        }
)
public class LoanEntity implements Serializable {

    @Id
    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "loan_application_id", nullable = false, unique = true)
    private String loanApplicationId;

    @Column(name = "loan_number", nullable = false, unique = true)
    private String loanNumber;

    @Column(name = "create_at", nullable = false)
    private LocalDate createAt;

    @Column(name = "start_at", nullable = false)
    private LocalDate startAt;

    @Column(name = "amount_approved", nullable = false, scale = 2, precision = 2)
    private Double amountApproved;

    @Column(name = "calculation_type")
    private AmortizationType amortizationType;

    @Column(name = "number_installments")
    private Integer numberInstallments;

    @Column(name = "frequency_payment")
    private FrequencyPayment frequencyPayment;

    @Column(name = "status", nullable = false)
    private LoanStatus status;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AmortizationScheduleEntity> amortizationSchedule;

    @OneToOne(mappedBy = "loan")
    private LatePaymentRateLoanEntity latePaymentRateLoan;

    @OneToOne(mappedBy = "loan")
    private InterestRateLoanEntity interestRateLoan;

    public static LoanEntity modelToEntity(Loan model){
        var loanEntity = LoanEntity.builder()
                .loanId(model.getLoanId())
                .loanApplicationId(model.getLoanApplicationId())
                .loanNumber(model.getLoanNumber())
                .amountApproved(model.getAmountApproved())
                .createAt(model.getCreateAt())
                .startAt(model.getStartAt())
                .frequencyPayment(model.getFrequencyPayment())
                .numberInstallments(model.getNumberInstallments())
                .amortizationType(model.getAmortizationType())
                .interestRateLoan(InterestRateLoanEntity.modelToEntity(model.getInterestRateLoan()))
                .latePaymentRateLoan(LatePaymentRateLoanEntity.modelToEntity(model.getLatePaymentRateLoan()))
                .status(model.getStatus())
                .build();
        var amortizationScheduleEntity = model.getAmortizationScheduleList().stream().map(
                amortizationSchedule-> AmortizationScheduleEntity.modelToEntity(amortizationSchedule, loanEntity)
        ).toList();

        loanEntity.setAmortizationSchedule(amortizationScheduleEntity);
        return loanEntity;
    }
}
