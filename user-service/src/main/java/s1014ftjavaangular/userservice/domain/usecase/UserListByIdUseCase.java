package s1014ftjavaangular.userservice.domain.usecase;

import s1014ftjavaangular.userservice.domain.model.dto.response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserListByIdUseCase {

    UserResponse findById(String id);
}
