package s1014ftjavaangular.userservice.domain.repository;

import s1014ftjavaangular.userservice.domain.model.dto.request.AccountCreatedDto;
import s1014ftjavaangular.userservice.domain.model.dto.request.UserRequest;
import s1014ftjavaangular.userservice.domain.model.dto.response.UserResponse;
import s1014ftjavaangular.userservice.domain.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository  {
    String findLastUserNumber(final String type);
    List<User> findAll();
    List<User> findAllByType(final String type);
    Optional<User> findById(final String id);
    void saveUser(final User model);

    void update(final User model);
}
