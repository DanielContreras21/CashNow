package s1014ftjavaangular.loan.domain.services;

import org.springframework.cglib.core.Local;
import s1014ftjavaangular.loan.domain.model.entities.AmortizationSchedule;
import s1014ftjavaangular.loan.domain.model.entities.Loan;
import s1014ftjavaangular.loan.domain.model.enums.AmortizationStatus;
import s1014ftjavaangular.loan.domain.model.enums.FrequencyPayment;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static s1014ftjavaangular.loan.domain.util.UtilitiesCalculations.*;

public class SimpleInterestStrategy extends AmortizationStrategy {

    private Double calculateCapital(Double capital, Integer numberInstallments) {
        return capital / numberInstallments;
    }

    private Double calculateInterest(Double capital, Double annualInterest, Integer numberInstallments, LocalDate currentDate, FrequencyPayment frequencyPayment) {
        //Dias del año
        Integer daysOfYears = daysInYear(currentDate);
        //Tasa diaria en porcentaje
        Double interestDailyPercentage = annualInterest / daysOfYears;
        //Tasa mensual en porcentaje
        Double percentageMonthly = Double.valueOf(formatDecimal(interestDailyPercentage * getNumberDaysInThisMonth(currentDate)));
        //Double percentageMonthly2 = interstAnnualPercentage/12;
        Double amortizationInterest = (capital * percentageMonthly / HUNDRED_PERCENT * 1) / numberInstallments;
        if (FrequencyPayment.MONTHLY.getValue() == frequencyPayment.getValue()) {
            percentageMonthly = annualInterest / NUMBER_OF_MONTHS;
        }
        if (FrequencyPayment.DAILY.getValue() == frequencyPayment.getValue()) {
            percentageMonthly = annualInterest / daysOfYears;
        }
        if (FrequencyPayment.WEEKLY.getValue() == frequencyPayment.getValue()) {
        percentageMonthly = annualInterest / WEEKS_OF_YEAR;
        }
        if (FrequencyPayment.BI_WEEKLY.getValue() == frequencyPayment.getValue()){
            percentageMonthly = annualInterest/ BI_WEEKLY;
        }
        Double interestPayment = capital * percentageMonthly / HUNDRED_PERCENT;

        return Double.valueOf(formatDecimal(interestPayment));
    }

    @Override
    public List<AmortizationSchedule> generateTable(Loan model) {
        //Numero de cuotas
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
            var capitalInstallment = calculateCapital(
                    model.getAmountApproved(),
                    numbersInstallments
            );

            var capitalBalance = formatDecimal(
                    (i == 1)
                            ? model.getAmountApproved() - capitalInstallment
                            : amortizationSchedules.get(i - 2).getCapitalBalance() - capitalInstallment
            );

            if (i == numbersInstallments && capitalBalance != 0) {
                capitalInstallment += capitalBalance;
                capitalBalance = 0.00;
            }

            var interestInstallment = calculateInterest(
                    (i == 1) ? model.getAmountApproved() : amortizationSchedules.get(i - 2).getCapitalBalance(),
                    model.getInterestRateLoan().getAnnualPercentage(),
                    numbersInstallments,
                    currentPaymentDate,
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
