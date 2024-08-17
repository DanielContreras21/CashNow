package s1014ftjavaangular.loan.domain.model.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import s1014ftjavaangular.loan.domain.model.enums.DaysOfWeek;
import s1014ftjavaangular.loan.infrastructure.persistence.entities.AmortizationScheduleEntity;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder
public class LoanDTO {
    @NotEmpty(message = "Application Loan Id cannot be empty")
    String applicationLoanId;
    @NotNull(message = "Amount approved cannot be empty")
    Double amountApproved;
    @NotNull(message = "Start Date cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startAt;
    @NotNull(message = "Numbers installments cannot be empty")
    Integer numberInstallments;
    @NotEmpty(message = "Calculation Type cannot be empty")
    String amortizationType;
    @NotEmpty(message = "Frequency payment cannot be empty")
    String frequencyPayment;
    @NotNull(message = "Annual percentage cannot be empty")
    Double annualPercentage;
    @NotEmpty(message = "Interest name cannot be empty")
    String interestName;
    @NotNull(message = "Late payment percentage cannot be empty")
    Double latePaymentAnnualPercentage;
    @NotEmpty(message = "Late payment name cannot be empty")
    String latePaymentName;
    @NotNull(message = "Expired date cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate expiredDate;
    List<DaysOfWeek> daysOfWeekList;
    List<AmortizationScheduleEntity> amortizationSchedule;
}