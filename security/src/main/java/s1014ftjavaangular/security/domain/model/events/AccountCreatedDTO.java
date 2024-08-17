package s1014ftjavaangular.security.domain.model.events;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountCreatedDTO extends Event {
    private String accountUuid;
    private String accountRol;
    private String name;
    private String lastname;
}
