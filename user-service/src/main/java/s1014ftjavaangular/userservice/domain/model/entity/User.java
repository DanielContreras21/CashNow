package s1014ftjavaangular.userservice.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s1014ftjavaangular.userservice.domain.model.enums.Genre;
import s1014ftjavaangular.userservice.domain.model.enums.CivilStatus;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String id;
    private String identifier;
    private String identifierNumber;
    private String number;
    private String type;
    private Genre genre;
    private String name;
    private String lastName;
    private LocalDate birthDay;
    private String phone;
    private String nationality;
    private ResidenceDetails residenceDetails;
    private Boolean blackList;
}
