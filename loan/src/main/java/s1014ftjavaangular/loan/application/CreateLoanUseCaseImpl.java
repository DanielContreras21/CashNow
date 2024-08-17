package s1014ftjavaangular.loan.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import s1014ftjavaangular.loan.domain.mappers.LoanMapper;
import s1014ftjavaangular.loan.domain.model.dtos.LoanDTO;
import s1014ftjavaangular.loan.domain.model.entities.AmortizationSchedule;
import s1014ftjavaangular.loan.domain.model.entities.Loan;
import s1014ftjavaangular.loan.domain.model.enums.AmortizationType;
import s1014ftjavaangular.loan.domain.model.enums.DaysOfWeek;
import s1014ftjavaangular.loan.domain.model.enums.FrequencyPayment;
import s1014ftjavaangular.loan.domain.model.enums.LoanStatus;
import s1014ftjavaangular.loan.domain.repository.InterestRateRepository;
import s1014ftjavaangular.loan.domain.repository.LatePaymentRateRepository;
import s1014ftjavaangular.loan.domain.repository.LoanRepository;
import s1014ftjavaangular.loan.domain.services.*;
import s1014ftjavaangular.loan.domain.usecase.CreateLoanUseCase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static s1014ftjavaangular.loan.domain.util.UtilitiesCalculations.calculationStrategies;

@Service
@RequiredArgsConstructor
public final class CreateLoanUseCaseImpl implements CreateLoanUseCase {
    private final LoanRepository repository;
    private final InterestRateRepository interestRateRepository;
    private final LatePaymentRateRepository latePaymentRateRepository;
    private final LoanMapper mapper;
    private final AmortizationService amortizationService;

    @Override
    public void createLoan(final LoanDTO loanDTO) {
        var lastLoanNumber = repository.getLastLoanNumber();
        int anoActual = LocalDate.now().getYear();
        var number = createNextLoanNumber(lastLoanNumber);
        String nextLoanNumber = anoActual + "-" + number;
//
        var loan = mapper.getCreateDTOToModel().apply(loanDTO, nextLoanNumber);

        if(!interestRateRepository.verifyExistsName(loanDTO.getInterestName()))
            throw new RuntimeException("Interest rate name doesn't exists!");

        if(!latePaymentRateRepository.verifyExistsName(loanDTO.getLatePaymentName()))
            throw new RuntimeException("Late paymente rate name doesn't exists!");

        loan.setInterestRateLoan(mapper.storeInterestRateLoan(loan.getLoanId(), loanDTO));
        loan.setLatePaymentRateLoan(mapper.storeLatePaymentRateLoan(loan.getLoanId(), loanDTO));

        amortizationService.setAmortizationStrategy(calculationStrategies.get(loan.getAmortizationType()));
        var amortizationSchedules =amortizationService.generate(loan);
        loan.setAmortizationScheduleList(amortizationSchedules);
        repository.saveLoan(loan);
    }

    private String createNextLoanNumber(String lastLoanNumber){
        if (!StringUtils.hasText(lastLoanNumber)) {
            return "1";
        } else {
            int separatorIndex = lastLoanNumber.indexOf("-");

            if (separatorIndex != -1 && separatorIndex + 1 < lastLoanNumber.length()) {
                String numeration = lastLoanNumber.substring(separatorIndex + 1);
                int number = Integer.parseInt(numeration.trim());
                number++;
                return String.valueOf(number);
            } else {
                return "1";
            }
        }
    }
}
