package s1014ftjavaangular.loan.domain.services;

import s1014ftjavaangular.loan.domain.model.entities.AmortizationSchedule;
import s1014ftjavaangular.loan.domain.model.entities.Loan;
import s1014ftjavaangular.loan.domain.model.enums.AmortizationStatus;
import s1014ftjavaangular.loan.domain.model.enums.FrequencyPayment;
import static s1014ftjavaangular.loan.domain.util.UtilitiesCalculations.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class CreditLineStrategy extends AmortizationStrategy{

    public Double calculateCapital(Double capital, Integer numberInstallments, Integer currentInstallment) {

        if (currentInstallment != numberInstallments) {
            return 0.0;
        }
        return capital;
    }

    public Double calculateInterest(Double capital, Double annualInterest, Integer numberInstallments, LocalDate currentDate, Integer currentInstallment, FrequencyPayment frequencyPayment) {
        //Dias del año
        Integer daysOfYears = daysInYear(currentDate);

        Double porcentage = (annualInterest / HUNDRED_PERCENT);
        Double interestInstallmentAnnual = capital * porcentage;
        Double interestInstallmentMonthly = interestInstallmentAnnual / NUMBER_OF_MONTHS;

        if (currentInstallment == numberInstallments) {
            return 0.0;
        }
        if (FrequencyPayment.WEEKLY.getValue() == frequencyPayment.getValue()){
            interestInstallmentMonthly = interestInstallmentAnnual / WEEKS_OF_YEAR;
        }
        if (FrequencyPayment.BI_WEEKLY.getValue() == frequencyPayment.getValue()){
            interestInstallmentMonthly = interestInstallmentAnnual / BI_WEEKLY;
        }
        if (FrequencyPayment.DAILY.getValue() == frequencyPayment.getValue()){
            interestInstallmentMonthly = interestInstallmentAnnual / daysOfYears;
        }

        return interestInstallmentMonthly;
    }

    @Override
    public List<AmortizationSchedule> generateTable(Loan model) {

        List<AmortizationSchedule> amortizationSchedules = new LinkedList<>();
        // Número de cuotas
        Integer numbersInstallments = model.getNumberInstallments();

        for (int i = 1; i <= numbersInstallments; i++) {
            var currentPaymentDate = (i == 1)
                    ? model.getStartAt()
                    : calculateNextLoanDate(
                    amortizationSchedules.get(i - 2).getPaymentDate(),
                    model.getStartAt(),
                    model.getFrequencyPayment().getValue(),
                    model.getFrequencyPayment()
            );
            var capitalInstallment = calculateCapital(
                    model.getAmountApproved(),
                    numbersInstallments,
                    i
            );

            var capitalBalance = (i == 1)
                    ? model.getAmountApproved() - capitalInstallment
                    : amortizationSchedules.get(i - 2).getCapitalBalance() - capitalInstallment;

            var interestInstallment = calculateInterest(
                    model.getAmountApproved(),
                    model.getInterestRateLoan().getAnnualPercentage(),
                    model.getNumberInstallments(),
                    currentPaymentDate,
                    i,
                    model.getFrequencyPayment()
            );

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
