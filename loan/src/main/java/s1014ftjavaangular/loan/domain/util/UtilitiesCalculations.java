package s1014ftjavaangular.loan.domain.util;

import s1014ftjavaangular.loan.domain.model.enums.AmortizationType;
import s1014ftjavaangular.loan.domain.model.enums.FrequencyPayment;
import s1014ftjavaangular.loan.domain.services.*;

import java.time.LocalDate;
import java.util.Map;

public class UtilitiesCalculations {

    public static final Integer NUMBER_OF_MONTHS = 12;
    public static final  Integer HUNDRED_PERCENT = 100;
    public static final Integer DAYS_OF_YEAR = 365;
    public static final Integer WEEKS_OF_YEAR = 52;
    public static final Integer BI_WEEKLY = 26;
    public static final Integer DAYS_OF_YEAR_IN_LEAP = 366;

    public static final Map<AmortizationType, AmortizationStrategy> calculationStrategies = Map.of(
                    AmortizationType.CREDIT_LINE, new CreditLineStrategy(),
                    AmortizationType.FIXED_INSTALLMENTS, new FixedInstallmentsStrategy(),
                    AmortizationType.SIMPLE_INTEREST, new SimpleInterestStrategy(),
                    AmortizationType.OUTSTANDING_INTEREST, new OutstandingInterestStrategy()
            );


    public static LocalDate calculateNextLoanDate(LocalDate currentDate, LocalDate startDate, Integer timeAdd, FrequencyPayment frequencyPayment){
        var monthNumber = currentDate.getMonth().getValue();
        if(frequencyPayment == FrequencyPayment.MONTHLY){
            if(isFebruaryTheMonth(monthNumber+1) && dateOlderThanTwentyEight(startDate)){
                return currentDate.isLeapYear()
                        ? LocalDate.of(currentDate.getYear(), 2, 29)
                        : LocalDate.of(currentDate.getYear(), 2, 28);
            }

            if(isFebruaryTheMonth(monthNumber)){
                return LocalDate.of(currentDate.getYear(), 3, startDate.getDayOfMonth());
            }

            if(isTheNextMonthHas31Days(monthNumber) && startDate.getDayOfMonth() == 31){
                var next = currentDate.plusMonths(1);
                return LocalDate.of(next.getYear(), next.getMonth(), 31);
            }

            return currentDate.plusMonths(timeAdd);
        }

        return currentDate.plusDays(timeAdd);
    }

    public static Boolean isFebruaryTheMonth(Integer monthNumber){
        return (monthNumber == 2);
    }

    public static Boolean dateOlderThanTwentyEight(LocalDate startDate){
        return (startDate.getDayOfMonth() == 29 || startDate.getDayOfMonth() == 30 || startDate.getDayOfMonth() == 31);
    }


    public static Boolean isTheNextMonthHas31Days(Integer monthNumber){
        return (monthNumber == 12) ||
                (monthNumber == 2) ||
                (monthNumber == 4) ||
                (monthNumber == 6) ||
                (monthNumber == 7) ||
                (monthNumber == 9) ||
                (monthNumber == 11);
    }

    public static Boolean isAMonthOfThirtyOneDays(Integer daysOfMonthsNumber){
        return daysOfMonthsNumber == 31;
    }
}
