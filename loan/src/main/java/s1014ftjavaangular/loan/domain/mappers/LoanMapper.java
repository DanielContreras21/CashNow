package s1014ftjavaangular.loan.domain.mappers;

import lombok.Data;
import org.springframework.stereotype.Component;
import s1014ftjavaangular.loan.domain.model.dtos.LoanDTO;
import s1014ftjavaangular.loan.domain.model.entities.AmortizationSchedule;
import s1014ftjavaangular.loan.domain.model.entities.InterestRateLoan;
import s1014ftjavaangular.loan.domain.model.entities.LatePaymentRateLoan;
import s1014ftjavaangular.loan.domain.model.entities.Loan;
import s1014ftjavaangular.loan.domain.model.enums.AmortizationType;
import s1014ftjavaangular.loan.domain.model.enums.FrequencyPayment;
import s1014ftjavaangular.loan.domain.model.enums.LoanStatus;
import s1014ftjavaangular.loan.infrastructure.persistence.entities.LoanEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;


@Data
@Component
public class LoanMapper {

    private BiFunction<LoanDTO, String, Loan> createDTOToModel =
            (loanDTO, loanNumber)-> Loan.builder()
                        .loanId(UUID.randomUUID().toString())
                        .loanApplicationId(loanDTO.getApplicationLoanId())
                        .loanNumber(loanNumber)
                        .amountApproved(loanDTO.getAmountApproved())
                        .numberInstallments(loanDTO.getNumberInstallments())
                        .amortizationType(AmortizationType.stringToEnum(loanDTO.getAmortizationType()))
                        .frequencyPayment(FrequencyPayment.stringToEnum(loanDTO.getFrequencyPayment()))
                        .createAt(LocalDate.now())
                        .startAt(loanDTO.getStartAt())
                        .status(LoanStatus.NORMAL)
                    .build();

    public InterestRateLoan storeInterestRateLoan(String id, LoanDTO dto){
        return  InterestRateLoan.builder()
                .loanId(id)
                .name(dto.getInterestName())
                .annualPercentage(dto.getAnnualPercentage())
                .expiredDate(dto.getExpiredDate())
                .build();

    }

    public LatePaymentRateLoan storeLatePaymentRateLoan(String id, LoanDTO dto){
        return  LatePaymentRateLoan.builder()
                .loanId(id)
                .name(dto.getLatePaymentName())
                .annualPercentage(dto.getLatePaymentAnnualPercentage())
                .expiredDate(dto.getExpiredDate())
                .build();

    }
}
