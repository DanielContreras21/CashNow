package s1014ftjavaangular.loansapplication.domain.repository;

import s1014ftjavaangular.loansapplication.domain.model.entity.JobInformation;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;

public interface JobInformationRepository {
    void deleteJobInformation(final String id);

    void saveJobInformation(final JobInformation model, final LoanApplication loanApplication);
}

