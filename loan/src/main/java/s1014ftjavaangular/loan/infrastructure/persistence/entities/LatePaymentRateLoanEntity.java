package s1014ftjavaangular.loan.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import s1014ftjavaangular.loan.domain.model.entities.InterestRateLoan;
import s1014ftjavaangular.loan.domain.model.entities.LatePaymentRateLoan;

import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "late_payment_rate_loan")
public class LatePaymentRateLoanEntity {
    @Id
    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "annual_percentage", nullable = false)
    private Double annualPercentage;

    @Column(name = "expired_date", nullable = false)
    private LocalDate expiredDate;

    @ToString.Exclude
    @JsonIgnore
    @MapsId
    @OneToOne
    @JoinColumn(name = "loan_id", referencedColumnName = "loan_id", foreignKey = @ForeignKey(name = "late_payment_rate_loan_fk"))
    private LoanEntity loan;


    public static LatePaymentRateLoanEntity modelToEntity(LatePaymentRateLoan model){
        return LatePaymentRateLoanEntity.builder()
                .loanId(model.getLoanId())
                .name(model.getName())
                .annualPercentage(model.getAnnualPercentage())
                .expiredDate(model.getExpiredDate())
                .build();
    }
}
