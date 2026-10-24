package com.projeto.vendora.handler;

import com.projeto.vendora.exception.EmailAlreadyExistsException;
import com.projeto.vendora.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ProblemDetail buildProblem(HttpStatus status, String title, String detail) {
        var problemDetail = ProblemDetail.forStatusAndDetail(
                status,
                detail
        );

        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setTitle(title);

        return problemDetail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex) {
        return  buildProblem(HttpStatus.NOT_FOUND, "Resource not found!", ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ProblemDetail handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        return buildProblem(HttpStatus.CONFLICT, "Email already exists!", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleTypeMismatch(MethodArgumentTypeMismatchException ex){
        String requiredType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconhecido";
        return buildProblem(HttpStatus.BAD_REQUEST,"Invalid parameter type", "The parameter'" + ex.getName()
                + "'  received an invalid value. The expected type is " +requiredType );
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGlobalException(Exception ex) {
        return buildProblem(HttpStatus.INTERNAL_SERVER_ERROR, "Intenal Server error",  ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationError(MethodArgumentNotValidException ex) {
        var problemDetail = buildProblem(HttpStatus.BAD_REQUEST, "Validation Error", ex.getMessage());

        Map<String, String> invalidFields = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid field",
                        (existingMessage, newMessage) -> existingMessage
                ));

        problemDetail.setProperty("invalid_fields", invalidFields);

        return problemDetail;
    }
}
