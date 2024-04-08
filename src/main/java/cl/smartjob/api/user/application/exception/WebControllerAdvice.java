package cl.smartjob.api.user.application.exception;

import cl.smartjob.api.user.application.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.atomic.AtomicReference;

@RestControllerAdvice
public class WebControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handlerEmailException(UserException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerValidate(MethodArgumentNotValidException ex) {
        AtomicReference<String> message = new AtomicReference<>("");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            message.set(errorMessage);
        });
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message.get());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

}
