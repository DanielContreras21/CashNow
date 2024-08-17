package s1014ftjavaangular.loan.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import s1014ftjavaangular.loan.domain.repository.InterestRateRepository;

@Repository
@RequiredArgsConstructor
public class InterestRateRepositoryAdapter implements InterestRateRepository {
    private final InterestRateJpaRepository repository;


    @Override
    public Boolean verifyExistsName(String interestName) {

        return repository.existsByName(interestName);
    }
}
