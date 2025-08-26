package s1014ftjavaangular.loansapplication.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import s1014ftjavaangular.loansapplication.domain.mapper.GeneralDataMapper;
import s1014ftjavaangular.loansapplication.domain.mapper.LoanApplicationMapper;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.GeneralDataDto;
import s1014ftjavaangular.loansapplication.domain.repository.GeneralDataRepository;
import s1014ftjavaangular.loansapplication.domain.repository.LoanApplicationRepository;
import s1014ftjavaangular.loansapplication.domain.usecase.SaveGeneralDataUseCase;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveGeneralDataUseCaseImpl implements SaveGeneralDataUseCase {
    private final GeneralDataRepository generalDataRepository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final GeneralDataMapper generalDataMapper;
    private final LoanApplicationMapper loanApplicationMapper;

//
    @Override
    public String saveGeneralData(GeneralDataDto request) {

        var countIncompleteOrAuditingLoanApplication = loanApplicationRepository.countOfInactiveOrAuditingLoanApplicatin(request.getIdentification());
        if(countIncompleteOrAuditingLoanApplication == 1){
            throw new RuntimeException("Cannot create new request because you already have one in 'AUDITING' or 'INCOMPLETE' status");
        }

        var actualYear = LocalDate.now().getYear();
        var lastLoanApplication = loanApplicationRepository.findLastLoanApplicationNumber();
        var nextNumber = getNextLoanApplicationNumber(lastLoanApplication);
        String loanApplicationNumber = actualYear + "-" + nextNumber;
        var loanApplication = loanApplicationMapper.dtoToModel.apply(request, loanApplicationNumber);

        this.loanApplicationRepository.saveLoanApplication(loanApplication);

        request.setLoanApplicationId(loanApplication.getLoanApplicationId());
        var generalData = generalDataMapper.dtoToModel.apply(request);
        generalDataRepository.saveGeneralData(generalData, loanApplication);

        return loanApplication.getLoanApplicationId();
    }

    private String getNextLoanApplicationNumber(String lastLoanApplicationNumber) {
        if (!StringUtils.hasText(lastLoanApplicationNumber)) {
            return "1";
        } else {
            int separatorIndex = lastLoanApplicationNumber.indexOf("-");

            if (separatorIndex != -1 && separatorIndex + 1 < lastLoanApplicationNumber.length()) {
                String numeration = lastLoanApplicationNumber.substring(separatorIndex + 1);
                int number = Integer.parseInt(numeration.trim());
                number++;
                return String.valueOf(number);
            } else {
                return "1";
            }
        }
    }
}
