package s1014ftjavaangular.loansapplication.domain.usecase;

import s1014ftjavaangular.loansapplication.domain.model.dto.request.GuarantorDto;

public interface SaveGuarantorUseCase {

    void saveGuarantor(GuarantorDto request);

}
