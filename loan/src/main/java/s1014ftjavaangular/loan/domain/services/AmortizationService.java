package s1014ftjavaangular.loan.domain.services;

import s1014ftjavaangular.loan.domain.model.entities.AmortizationSchedule;
import s1014ftjavaangular.loan.domain.model.entities.Loan;
import s1014ftjavaangular.loan.domain.model.enums.FrequencyPayment;

import java.time.LocalDate;
import static s1014ftjavaangular.loan.domain.util.UtilitiesCalculations.*;
import java.util.List;


public interface AmortizationService {
    List<AmortizationSchedule> generate(Loan model);
    void setAmortizationStrategy(AmortizationStrategy strategy);

}
