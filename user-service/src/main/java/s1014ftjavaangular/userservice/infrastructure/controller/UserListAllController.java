package s1014ftjavaangular.userservice.infrastructure.controller;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s1014ftjavaangular.userservice.domain.model.dto.response.UserResponse;
import s1014ftjavaangular.userservice.domain.usecase.UserListFindAllUseCase;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserListAllController {
    private final UserListFindAllUseCase userListFindAllUseCase;

    @Retry(name = "userRetry")
    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAll(){
        var response = userListFindAllUseCase.findAll();

        return response.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(response);
    }

}
