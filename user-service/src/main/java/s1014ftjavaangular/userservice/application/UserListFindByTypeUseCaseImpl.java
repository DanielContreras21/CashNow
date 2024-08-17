package s1014ftjavaangular.userservice.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s1014ftjavaangular.userservice.domain.model.dto.response.UserResponse;
import s1014ftjavaangular.userservice.domain.model.entity.User;
import s1014ftjavaangular.userservice.domain.model.mapper.UserMapper;
import s1014ftjavaangular.userservice.domain.repository.UserRepository;
import s1014ftjavaangular.userservice.domain.usecase.UserListByTypeUseCase;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserListFindByTypeUseCaseImpl implements UserListByTypeUseCase {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public List<UserResponse> findAllByType(String type) {
        List<User> models = userRepository.findAllByType(type);
//
        return models.stream().map(model-> mapper.userModelToResponse(model)).toList();

    }
}
