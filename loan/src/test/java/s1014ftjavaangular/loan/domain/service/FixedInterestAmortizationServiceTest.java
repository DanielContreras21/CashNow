package s1014ftjavaangular.loan.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import s1014ftjavaangular.loan.domain.model.entities.InterestRateLoan;
import s1014ftjavaangular.loan.domain.model.entities.Loan;
import s1014ftjavaangular.loan.domain.model.enums.AmortizationType;
import s1014ftjavaangular.loan.domain.model.enums.FrequencyPayment;
import s1014ftjavaangular.loan.domain.model.enums.LoanStatus;
import s1014ftjavaangular.loan.domain.services.AmortizationService;
import s1014ftjavaangular.loan.domain.services.FixedInstallmentsStrategy;
import s1014ftjavaangular.loan.domain.services.SimpleInterestStrategy;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FixedInterestAmortizationServiceTest {

    @Autowired
    private AmortizationService amortizationService;

    private Loan model;

    @BeforeEach
    public void setUp() {

        var id = UUID.randomUUID().toString();
        this.model = Loan.builder()
                .loanId(id)
                .loanNumber("1")
                .createAt(LocalDate.now())
                .startAt(LocalDate.now())
                .amountApproved(8000.00)
                .amortizationType(AmortizationType.FIXED_INSTALLMENTS)
                .interestRateLoan(InterestRateLoan.builder()
                        .loanId(id)
                        .name("Interes de testeo")
                        .expiredDate(LocalDate.of(2024, 9, 3))
                        .annualPercentage(35.00)
                        .build())
                .numberInstallments(5)
                .status(LoanStatus.NORMAL)
                .build();

        amortizationService.setAmortizationStrategy(new FixedInstallmentsStrategy());
    }

        @DisplayName("Check when the schedule list is not null.")
        @Test
        public void verify_scheduleList_notNull() {
            model.setFrequencyPayment(FrequencyPayment.MONTHLY);

            var scheduleList = amortizationService.generate(model);
            assertFalse(scheduleList.isEmpty());
        }

        @DisplayName("Check when in the first monthly installment is correct")
        @Test
        public void when_capital_in_first_installment_with_monthly_payment_is_correct() {
            model.setFrequencyPayment(FrequencyPayment.MONTHLY);

            var scheduleList = amortizationService.generate(model);

            var capital = scheduleList.get(0).getCapitalInstallment();
            assertTrue(capital != null);

            var expectedCapital = 1600.00;
            assertEquals(expectedCapital, capital);
        }

        @DisplayName("Check when in the first monthly insterest is correct")
        @Test
        public void when_interest_in_first_installment_with_monthly_payment_is_correct() {
            model.setFrequencyPayment(FrequencyPayment.MONTHLY);

            var scheduleList = amortizationService.generate(model);
            var interest = scheduleList.get(0).getInterest();
            assertTrue(interest != null);

            var expectedInterest = 233.33;
            assertEquals(expectedInterest, interest);
        }

        @DisplayName("Check when in the last monthly insterest is correct")
        @Test
        public void when_last_interest_with_monthly_payment_is_correct() {
            model.setFrequencyPayment(FrequencyPayment.MONTHLY);

            var scheduleList = amortizationService.generate(model);

            var interest = scheduleList.get(scheduleList.size() - 1).getInterest();

            var expectedInterest = 233.33;
            assertEquals(expectedInterest, interest);
        }

        @DisplayName("Check when in the first monthly capital balance is correct")
        @Test
        public void when_last_capital_balance_with_monthly_payment_is_correct() {
            model.setFrequencyPayment(FrequencyPayment.MONTHLY);

            var scheduleList = amortizationService.generate(model);

            var capitalBalance = scheduleList.get(scheduleList.size() - 1).getCapitalBalance();

            var expectedInterest = 0.00;
            assertEquals(expectedInterest, capitalBalance);
        }

}
