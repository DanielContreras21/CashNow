package s1014ftjavaangular.loan.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "interest_rate",
        uniqueConstraints = @UniqueConstraint(name = "uk_name", columnNames = "name")
)
public class InterestRatesEntity {
    @Id
    @Column(name = "interest_rate_id")
    private String interestRatesUuid;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "annual_percentage", nullable = false)
    private Double annualPercentage;
    @Column(name = "status", nullable = false)
    private Boolean status;
}
