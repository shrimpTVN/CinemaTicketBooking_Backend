package com.cinema.ticketbooking.core.exception;

import com.cinema.ticketbooking.dto.responseDto.ErrorResponseDto;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentException(MethodArgumentNotValidException exception){
        Map<String, String> errors =new HashMap<>();
        List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();
        fieldErrorList.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);

    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, String>> handleMethodException(HandlerMethodValidationException exception){
        Map<String, String> errors =new HashMap<>();
        List<ParameterValidationResult> results = exception.getParameterValidationResults();
        results.forEach(result -> {
            String paramName = result.getMethodParameter().getParameterName();

            String combineMsg = result.getResolvableErrors()
                    .stream()
                    .map(MessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            errors.put(paramName, combineMsg);
        });
        return ResponseEntity.badRequest().body(errors);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception, WebRequest webRequest) {
        System.out.println("Exception: " + exception.getMessage());
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred", LocalDateTime.now()
        );
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFound(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false), HttpStatus.NOT_FOUND,
                exception.getMessage(), LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequest(IllegalArgumentException exception, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false), HttpStatus.BAD_REQUEST,
                exception.getMessage(), LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> handleAccessDeniedException(AccessDeniedException ex, WebRequest webRequest) {
        System.out.println("AccessDeniedException: " + ex.getMessage());
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                webRequest.getDescription(false), HttpStatus.FORBIDDEN,
                "Access Denied: Bạn không có quyền (Role) để thực hiện hành động này.",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
