package s1014ftjavaangular.loan.domain.services;

import s1014ftjavaangular.loan.domain.model.entities.AmortizationSchedule;
import s1014ftjavaangular.loan.domain.model.entities.Loan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import static s1014ftjavaangular.loan.domain.util.UtilitiesCalculations.DAYS_OF_YEAR;
import static s1014ftjavaangular.loan.domain.util.UtilitiesCalculations.DAYS_OF_YEAR_IN_LEAP;

public abstract class AmortizationStrategy {
    abstract List<AmortizationSchedule> generateTable(Loan model);

    public Double formatDecimal(Double amount){
        return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }


    protected Integer daysInYear(LocalDate date) {
        return isLeapYears(date) ? DAYS_OF_YEAR_IN_LEAP : DAYS_OF_YEAR;
    }

    protected Boolean isLeapYears(LocalDate date){
        return date.isLeapYear();
    }

    protected Integer getNumberDaysInThisMonth(LocalDate currentDate){
        return currentDate.getMonth().length(isLeapYears(currentDate));
    }
}
