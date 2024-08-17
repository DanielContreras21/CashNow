package s1014ftjavaangular.loan.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(
        name = "late_payment_rate",
        uniqueConstraints = @UniqueConstraint(name = "uk_name", columnNames = "name")
)
public class LatePaymentRateEntity {

    @Id
    @Column(name = "late_payment_rate_id")
    private String latePaymentRateId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "annual_percentage", nullable = false)
    private Double  annualPercentage;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "day_of_grace", nullable = false)
    private String dayOfGrace;
}
