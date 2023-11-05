package com.gamertx.exceptions;

import com.gamertx.domain.dto.ErrorDetails;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resolveNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(ZoneId.systemDefault()), exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExist.class)
    public ResponseEntity<ErrorDetails> resolveAlreadyExist(ResourceAlreadyExist exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(ZoneId.systemDefault()), exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(ZoneId.systemDefault()), exception.getMessage(),webRequest.getDescription(false));

        if (exception instanceof AccessDeniedException){
            return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nameField = ((FieldError)error).getField();
            String message = error.getDefaultMessage();

            errores.put(nameField,message);
        });
        return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);
    }
}
