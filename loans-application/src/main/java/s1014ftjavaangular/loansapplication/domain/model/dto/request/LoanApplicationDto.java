package s1014ftjavaangular.loansapplication.domain.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s1014ftjavaangular.loansapplication.domain.model.entity.CreditAudit;
import s1014ftjavaangular.loansapplication.domain.model.entity.GeneralData;
import s1014ftjavaangular.loansapplication.domain.model.entity.Guarantor;
import s1014ftjavaangular.loansapplication.domain.model.entity.JobInformation;
import s1014ftjavaangular.loansapplication.domain.model.enums.Status;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoanApplicationDto {

    @NotNull
    private String loanApplicationId;
    @NotNull
    private String customersUuid;
    @NotNull
    private String loanApplicationNumber;
    @NotEmpty(message = "Field required")
    private Double requestedAmount;
    @NotEmpty(message = "Field required")
    private LocalDate createAt;
    @NotEmpty(message = "Field required")
    private JobInformation jobInformation;
    @NotEmpty(message = "Field required")
    private Guarantor guarantor;
    @NotEmpty(message = "Field required")
    private GeneralData generalData;
    @NotEmpty(message = "Field required")
    private CreditAudit creditAuditorId;
    @NotEmpty(message = "Field required")
    private Status status;
}
