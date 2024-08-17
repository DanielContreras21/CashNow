package s1014ftjavaangular.loan.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import s1014ftjavaangular.loan.domain.model.entities.AmortizationSchedule;
import s1014ftjavaangular.loan.domain.model.entities.Loan;
import s1014ftjavaangular.loan.domain.model.enums.AmortizationStatus;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "amortization_schedule")
public class AmortizationScheduleEntity {
//
    @Id
    @Column(name = "amortization_schedule_id")
    private String amortizationScheduleId;

    @Column(name = "payment_day", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "capital_instalment", nullable = false, scale = 2, precision = 2)
    private Double capitalInstalment;

    @Column(name = "interest", nullable = false, scale = 2,precision = 2)
    private Double interest;

    @Column(name = "capital_balance", nullable = false, scale = 2,precision = 2)
    private Double capitalBalance;

    @Column(name = "total_paid", nullable = false, scale = 2, precision = 2)
    private Double totalPaid;

    @Column(name = "status", nullable = false)
    private AmortizationStatus status;

    @JsonBackReference
    @OneToMany(mappedBy = "amortizationSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LateFeedDetailsEntity> lateFeedDetailEntities;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "loan_id", referencedColumnName = "loan_id", foreignKey = @ForeignKey(name = "amortization_loan_fk"))
    private LoanEntity loan;


    public static AmortizationScheduleEntity modelToEntity(AmortizationSchedule model, LoanEntity loanEntity){
        return AmortizationScheduleEntity.builder()
                .amortizationScheduleId(model.getAmortizationScheduleId())
                .loan(loanEntity)
                .paymentDate(model.getPaymentDate())
                .capitalInstalment(model.getCapitalInstallment())
                .capitalBalance(model.getCapitalBalance())
                .interest(model.getInterest())
                .totalPaid(model.getTotalPaid())
                .status(model.getStatus())
                .build();
    }
}
