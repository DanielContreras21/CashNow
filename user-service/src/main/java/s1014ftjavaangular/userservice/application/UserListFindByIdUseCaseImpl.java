package s1014ftjavaangular.userservice.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s1014ftjavaangular.userservice.domain.model.dto.response.UserResponse;
import s1014ftjavaangular.userservice.domain.model.mapper.UserMapper;
import s1014ftjavaangular.userservice.domain.repository.UserRepository;
import s1014ftjavaangular.userservice.domain.usecase.UserListByIdUseCase;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserListFindByIdUseCaseImpl implements UserListByIdUseCase {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public UserResponse findById(String id) {
        var model = userRepository.findById(id);
        return model.isEmpty() ? null : mapper.userModelToResponse(model.get());
    }
}
