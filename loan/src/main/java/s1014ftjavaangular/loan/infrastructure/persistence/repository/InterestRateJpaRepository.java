package s1014ftjavaangular.loan.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s1014ftjavaangular.loan.infrastructure.persistence.entities.InterestRatesEntity;

public interface InterestRateJpaRepository extends JpaRepository<InterestRatesEntity, String> {

    Boolean existsByName(String name);
}
