package s1014ftjavaangular.security.infrastructure.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s1014ftjavaangular.security.domain.model.dto.LoginDTO;
import s1014ftjavaangular.security.domain.model.dto.LoginResponse;
import s1014ftjavaangular.security.domain.usecase.LoginUseCase;

@RestController
@RequestMapping("/api/accounts/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginUseCase loginUseCase;

    @Retry(name = "securityRetry")
    @PostMapping()
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginDTO loginDto){
        var response = loginUseCase.login(loginDto.getEmail(), loginDto.getPassword());

        return ResponseEntity.ok(response);
    }

}
