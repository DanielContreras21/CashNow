package s1014ftjavaangular.loansapplication.domain.usecase;

import s1014ftjavaangular.loansapplication.domain.model.dto.request.JobInformationDto;

public interface UpdateJobInformationUseCase {
    void updateJobInformation(JobInformationDto request);
}
