package s1014ftjavaangular.loan.domain.repository;

public interface LatePaymentRateRepository {
    Boolean verifyExistsName(final String latePaymentName);
}
