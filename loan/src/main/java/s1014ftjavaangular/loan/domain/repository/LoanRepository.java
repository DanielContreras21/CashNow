package s1014ftjavaangular.loan.domain.repository;

import s1014ftjavaangular.loan.domain.model.entities.Loan;

public interface LoanRepository {

    String getLastLoanNumber();

    void saveLoan(final Loan loan);
}
