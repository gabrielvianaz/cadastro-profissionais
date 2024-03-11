package tech.gviana.cadastroprofissionais.core.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorSchema> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(ErrorSchema.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .message(e.getFieldError().getDefaultMessage())
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorSchema> entityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status).body(ErrorSchema.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorSchema> runtimeException(RuntimeException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(ErrorSchema.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build());
    }

}
