package s1014ftjavaangular.loan.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import s1014ftjavaangular.loan.domain.model.entities.InterestRateLoan;
import s1014ftjavaangular.loan.domain.model.entities.LatePaymentRateLoan;
import s1014ftjavaangular.loan.domain.model.entities.Loan;
import s1014ftjavaangular.loan.domain.model.enums.LoanStatus;
import s1014ftjavaangular.loan.domain.repository.LoanRepository;
import s1014ftjavaangular.loan.infrastructure.persistence.entities.InterestRateLoanEntity;
import s1014ftjavaangular.loan.infrastructure.persistence.entities.LatePaymentRateEntity;
import s1014ftjavaangular.loan.infrastructure.persistence.entities.LatePaymentRateLoanEntity;
import s1014ftjavaangular.loan.infrastructure.persistence.entities.LoanEntity;

import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class LoanRepositoryAdapter implements LoanRepository {
    private final LoanJpaRepository repository;

    @Override
    public String getLastLoanNumber() {
        var number = repository.findLastLoanNumber();
        return number.isEmpty()
                ? ""
                : number.get();
    }

    @Override
    public void saveLoan(Loan loan) {
        var entity = LoanEntity.modelToEntity(loan);
        repository.save(entity);
    }
}
