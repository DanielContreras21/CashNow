package s1014ftjavaangular.loansapplication.domain.repository;

import s1014ftjavaangular.loansapplication.domain.model.entity.GeneralData;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;

public interface GeneralDataRepository {

    void deleteGeneralDataById(final String id);

    void saveGeneralData(final GeneralData model, final LoanApplication loanApplication);

}
