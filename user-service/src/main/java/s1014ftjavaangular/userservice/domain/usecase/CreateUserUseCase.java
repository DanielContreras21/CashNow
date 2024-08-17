package s1014ftjavaangular.userservice.domain.usecase;

import s1014ftjavaangular.userservice.domain.model.dto.request.AccountCreatedDto;

public interface CreateUserUseCase {
    void saveUser(AccountCreatedDto message);
}
