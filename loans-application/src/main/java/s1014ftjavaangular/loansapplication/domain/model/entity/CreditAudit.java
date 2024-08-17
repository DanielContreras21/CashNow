package s1014ftjavaangular.loansapplication.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreditAudit {
    private String loanApplicationId;
    private String creditAuditId;
    private LocalDate auditDate;
    private String creditAuditorId;
}
