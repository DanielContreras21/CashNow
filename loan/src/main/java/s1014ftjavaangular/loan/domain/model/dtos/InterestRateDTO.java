package s1014ftjavaangular.loan.domain.model.dtos;

import jakarta.validation.constraints.NotEmpty;

public class InterestRateDTO {
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "Annual Percentage cannot be empty")
    private Double annualPercentage;
}
