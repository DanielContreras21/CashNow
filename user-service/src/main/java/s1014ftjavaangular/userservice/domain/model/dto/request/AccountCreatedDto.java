package s1014ftjavaangular.userservice.domain.model.dto.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountCreatedDto implements Serializable {
    public static final Long serialVersionUID = 1L;

    private String accountUuid;
    private String accountRol;
    private String name;
    private String lastname;
}
