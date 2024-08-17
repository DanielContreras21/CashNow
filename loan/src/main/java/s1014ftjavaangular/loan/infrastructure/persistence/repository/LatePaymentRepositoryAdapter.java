package s1014ftjavaangular.loan.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import s1014ftjavaangular.loan.domain.repository.LatePaymentRateRepository;
@Repository
@RequiredArgsConstructor
public class LatePaymentRepositoryAdapter implements LatePaymentRateRepository {
    private final LatePaymentJpaRepository latePaymentJpaRepository;

    @Override
    public Boolean verifyExistsName(String latePaymentName) {
        return latePaymentJpaRepository.existsByName(latePaymentName);
    }
}
