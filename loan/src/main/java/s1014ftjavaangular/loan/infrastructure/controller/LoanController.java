package s1014ftjavaangular.loan.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s1014ftjavaangular.loan.domain.model.dtos.LoanDTO;
import s1014ftjavaangular.loan.domain.usecase.CreateLoanUseCase;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/loan")
@Slf4j
public class LoanController {
    private final CreateLoanUseCase useCase;

    @PostMapping("/create")
    public ResponseEntity<?> saveLoan(@RequestBody @Valid LoanDTO loanDto) {
        log.info(loanDto.toString());
        useCase.createLoan(loanDto);
        return ResponseEntity.ok().build();

    }
}

