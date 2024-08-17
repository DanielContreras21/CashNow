package s1014ftjavaangular.loan.domain.usecase;

import s1014ftjavaangular.loan.domain.model.dtos.LoanDTO;

public interface CreateLoanUseCase {
    void createLoan(final LoanDTO message);
}
