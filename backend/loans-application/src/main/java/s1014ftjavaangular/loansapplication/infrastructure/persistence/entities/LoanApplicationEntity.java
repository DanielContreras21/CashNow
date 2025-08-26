package s1014ftjavaangular.loansapplication.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;
import s1014ftjavaangular.loansapplication.domain.model.enums.Status;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="loans_application")
public class LoanApplicationEntity {
    @Id
    @Column(name = "loan_application_id")
    private String loanApplicationId;

    @Column(name = "customer_id", nullable = false)
    private String customersUuid;

    @Column(name = "loan_application_number", nullable = false, unique = true)
    private String loanApplicationNumber;

    @Column(name = "request_amount", nullable = false)
    private Double requestedAmount;

    @Column(name = "create_at", nullable = false)
    private LocalDate createAt;

    @OneToOne(mappedBy = "loansApplication", cascade = CascadeType.ALL)
    private JobInformationEntity jobInformation;

    @OneToOne(mappedBy = "loansApplication", cascade = CascadeType.ALL)
    private GuarantorEntity guarantor;

    @OneToOne(mappedBy = "loansApplication")
    private GeneralDataEntity generalData;

    @OneToOne(mappedBy = "loansApplicationId", cascade = CascadeType.ALL)
    private CreditAuditEntity creditAuditId;

    @Column(name = "status", nullable = false)
    private Status status;

    public static LoanApplicationEntity modelToEntity(LoanApplication model){
        return LoanApplicationEntity.builder()
                .loanApplicationId(model.getLoanApplicationId())
                .loanApplicationNumber(model.getLoanApplicationNumber())
                .customersUuid(model.getCustomersUuid())
                .requestedAmount(model.getRequestedAmount())
                .createAt(model.getCreateAt())
                .status(model.getStatus())

                .jobInformation(model.getJobInformation() != null ? JobInformationEntity.modelToEntity.apply(model.getJobInformation()) : null)
                .guarantor(model.getGuarantor() != null ? GuarantorEntity.modelToEntity.apply(model.getGuarantor()) : null)
                .generalData(model.getGeneralData() != null ? GeneralDataEntity.modelToEntity.apply(model.getGeneralData()) : null)

                .creditAuditId(model.getCreditAuditorId() != null ? CreditAuditEntity.modelToEntity.apply(model.getCreditAuditorId()) : null)
                .build();
    }

    public LoanApplication entityToModel(){
        return LoanApplication.builder()
                .loanApplicationId(this.getLoanApplicationId())
                .loanApplicationNumber(this.getLoanApplicationNumber())
                .requestedAmount(this.getRequestedAmount())
                .customersUuid(this.getCustomersUuid())
                .status(this.getStatus())
                .createAt(this.getCreateAt())
                .generalData(this.getGeneralData().entityToModel())
                .jobInformation(this.getJobInformation() != null ? this.getJobInformation().entityToModel() : null)
                .guarantor(this.getGuarantor() != null ? this.getGuarantor().entityToModel() : null)
                .build();

    }

}
