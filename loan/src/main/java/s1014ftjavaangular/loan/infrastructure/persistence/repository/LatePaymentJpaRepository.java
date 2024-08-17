package s1014ftjavaangular.loan.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s1014ftjavaangular.loan.infrastructure.persistence.entities.LatePaymentRateEntity;

public interface LatePaymentJpaRepository extends JpaRepository<LatePaymentRateEntity,String> {

    Boolean existsByName(String name);
}
