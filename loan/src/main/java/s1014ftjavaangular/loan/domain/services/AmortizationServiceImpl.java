package s1014ftjavaangular.loan.domain.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import s1014ftjavaangular.loan.domain.model.entities.AmortizationSchedule;
import s1014ftjavaangular.loan.domain.model.entities.Loan;
import s1014ftjavaangular.loan.domain.model.enums.AmortizationStatus;
import s1014ftjavaangular.loan.domain.model.enums.AmortizationType;
import s1014ftjavaangular.loan.domain.model.enums.FrequencyPayment;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class AmortizationServiceImpl implements AmortizationService {
    private AmortizationStrategy strategy;

    @Override
    public void setAmortizationStrategy(AmortizationStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public List<AmortizationSchedule> generate(Loan model) {
        return strategy.generateTable(model);
    }

}
