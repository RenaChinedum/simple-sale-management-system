package com.rena.simplemanagementsystem.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e
    ){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> userNotFound(EntityAlreadyExistException e, HttpServletRequest request){
        ExceptionResponse er = ExceptionResponse.builder()
                .errorMessage(e.getMessage())
                .errorPath(request.getRequestURI())
                .errorStatusCode(HttpStatus.NOT_FOUND)
                .errorTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(er,HttpStatus.NOT_FOUND);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFound(EntityNotFoundException e, HttpServletRequest request){
        ExceptionResponse er = ExceptionResponse.builder()
                .errorMessage(e.getMessage())
                .errorPath(request.getRequestURI())
                .errorStatusCode(HttpStatus.NOT_FOUND)
                .errorTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(er,HttpStatus.NOT_FOUND);
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorisedAccessException.class)
    public ResponseEntity<ExceptionResponse> unauthorisedAccess(UnauthorisedAccessException e, HttpServletRequest request){
        ExceptionResponse er = ExceptionResponse.builder()
                .errorMessage(e.getMessage())
                .errorPath(request.getRequestURI())
                .errorStatusCode(HttpStatus.UNAUTHORIZED)
                .errorTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(er,HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ExceptionResponse> passwordMismatch(PasswordMismatchException e, HttpServletRequest request){
        ExceptionResponse er = ExceptionResponse.builder()
                .errorMessage(e.getMessage())
                .errorPath(request.getRequestURI())
                .errorStatusCode(HttpStatus.BAD_REQUEST)
                .errorTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> customException(CustomException e, HttpServletRequest request){
        ExceptionResponse er = ExceptionResponse.builder()
                .errorMessage(e.getMessage())
                .errorPath(request.getRequestURI())
                .errorStatusCode(HttpStatus.EXPECTATION_FAILED)
                .errorTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(er,HttpStatus.EXPECTATION_FAILED);
    }
}
