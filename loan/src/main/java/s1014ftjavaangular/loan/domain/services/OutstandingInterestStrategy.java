package s1014ftjavaangular.loan.domain.services;

import s1014ftjavaangular.loan.domain.model.entities.AmortizationSchedule;
import s1014ftjavaangular.loan.domain.model.entities.Loan;
import s1014ftjavaangular.loan.domain.model.enums.AmortizationStatus;
import s1014ftjavaangular.loan.domain.model.enums.FrequencyPayment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static s1014ftjavaangular.loan.domain.util.UtilitiesCalculations.*;

public class OutstandingInterestStrategy extends AmortizationStrategy{

    public Double calculateCapital(Double capital, Double annualInterest, LocalDate currentDate, Integer numberInstallments, FrequencyPayment frequencyPayment){
        return formatDecimal(capital / numberInstallments);
    }

    public Double calculateInterest(Double capital, Double annualInterest, LocalDate currentDate, Integer numberInstallments, FrequencyPayment frequencyPayment) {
        Double interest = (annualInterest / NUMBER_OF_MONTHS)/HUNDRED_PERCENT;
        Double amountInterest = capital * interest;
        return formatDecimal(amountInterest);
    }

    @Override
    public List<AmortizationSchedule> generateTable(Loan model) {
        Integer numbersInstallments = model.getNumberInstallments();
        List<AmortizationSchedule> amortizationSchedules = new LinkedList<>();

        for (int i = 1; i <= numbersInstallments; i++) {
            var currentPaymentDate = (i == 1)
                    ? model.getStartAt()
                    : calculateNextLoanDate(
                    amortizationSchedules.get(i - 2).getPaymentDate(),
                    model.getStartAt(),
                    model.getFrequencyPayment().getValue(),
                    model.getFrequencyPayment()
            );

            var interestInstallment = calculateInterest(
                    (i == 1) ? model.getAmountApproved() : (amortizationSchedules.get(i - 2).getInterest() + model.getAmountApproved()),
                    model.getInterestRateLoan().getAnnualPercentage(),
                    currentPaymentDate,
                    numbersInstallments,
                    model.getFrequencyPayment()
            );
            var capitalInstallment = calculateCapital(
                    (i == 1) ? model.getAmountApproved() : amortizationSchedules.get(i - 2).getCapitalBalance(),
                    model.getInterestRateLoan().getAnnualPercentage(),
                    currentPaymentDate,
                    numbersInstallments,
                    model.getFrequencyPayment()
            );

            var capitalBalance = (i == 1)
                    ? model.getAmountApproved() - capitalInstallment
                    : amortizationSchedules.get(i - 2).getCapitalBalance() - capitalInstallment;

            if (i == numbersInstallments && capitalBalance != 0) {
                capitalInstallment += capitalBalance;
                capitalBalance = 0.00;
            }

            var amortizationSchedule = AmortizationSchedule.builder()
                    .loanId(model.getLoanId())
                    .amortizationScheduleId(UUID.randomUUID().toString())
                    .paymentDate(currentPaymentDate)
                    .capitalInstallment(formatDecimal(capitalInstallment))
                    .interest(formatDecimal(interestInstallment))
                    .capitalBalance(formatDecimal(capitalBalance))
                    .totalPaid(0.00)
                    .status(AmortizationStatus.CURRENT)
                    .build();

            amortizationSchedules.add(amortizationSchedule);
        }

        return amortizationSchedules;
    }
}
