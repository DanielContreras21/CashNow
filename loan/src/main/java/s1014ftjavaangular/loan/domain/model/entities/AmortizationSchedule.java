package s1014ftjavaangular.loan.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import s1014ftjavaangular.loan.domain.model.enums.AmortizationStatus;

import java.time.LocalDate;
import java.util.Date;
@Data
@Builder
@AllArgsConstructor
public class AmortizationSchedule {

    private String amortizationScheduleId;
    private String loanId;
    private LocalDate paymentDate;
    private Double capitalInstallment;
    private Double interest;
    private Double capitalBalance;
    private Double totalPaid;
    private AmortizationStatus status;
}
