package s1014ftjavaangular.userservice.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import s1014ftjavaangular.userservice.domain.model.dto.response.ExceptionDTO;
import s1014ftjavaangular.userservice.domain.model.exception.ResourceAlreadyExists;
import s1014ftjavaangular.userservice.domain.model.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandlers {

    //Este metodo responde a excepciones de tipo "Exception", es decir excepciones genericas
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionDTO> handlerExceptions(Exception exception) {
        var exceptionDto = ExceptionDTO.builder()
                .detail(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .type(exception.getClass().getTypeName())
                .build();

        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handlerConlictException(UserNotFoundException exception) {
        var exceptionDto = ExceptionDTO.builder()
                .detail(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .type(exception.getClass().getTypeName())
                .build();

        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ExceptionDTO> handleIllegalArgumentException(IllegalArgumentException exception) {
        var exceptionDto = ExceptionDTO.builder()
                .detail(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .type(exception.getClass().getTypeName())
                .build();

        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(value = ResourceAlreadyExists.class)
    public ResponseEntity<ExceptionDTO> handlerConlictException(ResourceAlreadyExists exception) {
        var exceptionDto = ExceptionDTO.builder()
                .detail(exception.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .type(exception.getClass().getTypeName())
                .build();

        return new ResponseEntity<>(exceptionDto, HttpStatus.CONFLICT);
    }
}
