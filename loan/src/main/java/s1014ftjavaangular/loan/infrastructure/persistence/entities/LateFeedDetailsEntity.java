package s1014ftjavaangular.loan.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(
        name = "late_feed_details"
)
public class LateFeedDetailsEntity {
    @Id
    @Column(name = "late_feed_id")
    private String lateFeedDetailsId;

    @Column(name = "late_feed_amount")
    private Double lateFeedAmount;

    @Column(name = "late_feed_paid")
    private Double lateFeedPaid;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "amortization_schedule_id", referencedColumnName = "amortization_schedule_id", foreignKey = @ForeignKey(name = "amortization_late_feed_fk"), updatable = false)
    private AmortizationScheduleEntity amortizationSchedule;
}
