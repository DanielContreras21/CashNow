package s1014ftjavaangular.loan.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import s1014ftjavaangular.loan.infrastructure.persistence.entities.LoanEntity;

import java.util.Optional;

public interface LoanJpaRepository extends JpaRepository<LoanEntity, String> {
    @Query(value = "SELECT MAX(l.loan_number) FROM loan l", nativeQuery = true)
    Optional<String> findLastLoanNumber();
}
