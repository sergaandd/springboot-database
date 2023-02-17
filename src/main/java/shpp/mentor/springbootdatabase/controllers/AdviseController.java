package shpp.mentor.springbootdatabase.controllers;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import shpp.mentor.springbootdatabase.models.ErrorDTO;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

@ControllerAdvice
public class AdviseController {
    String operationTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorDTO> processValidationError(HttpMessageNotReadableException ex, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang", locale) ;
        String error = ex.getMessage();
        ErrorDTO message = new ErrorDTO(operationTime, HttpStatus.BAD_REQUEST.value()
                , resourceBundle.getString("data_field_error")
                , error);
        return ResponseEntity
                .badRequest()
                .body(message);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorDTO> notFoundEntityError(EntityNotFoundException ex, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang", locale) ;
        String error = ex.getMessage();
        ErrorDTO message = new ErrorDTO(operationTime, HttpStatus.BAD_REQUEST.value()
                , resourceBundle.getString("entity_not_found")
                , error);
        return ResponseEntity
                .badRequest()
                .body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorDTO> dataAddFieldError(MethodArgumentNotValidException ex, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang", locale) ;
        String error = ex.getMessage();
        ErrorDTO message = new ErrorDTO(operationTime, HttpStatus.BAD_REQUEST.value()
                , resourceBundle.getString("data_field_error")
                , error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorDTO> dataUpdateFieldError(ConstraintViolationException ex, Locale locale){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang", locale) ;
        String error = ex.getMessage();
        ErrorDTO message = new ErrorDTO(operationTime, HttpStatus.BAD_REQUEST.value()
                , resourceBundle.getString("data_field_error")
                , error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorDTO> deleteError(EmptyResultDataAccessException ex,Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang", locale) ;
        String error = ex.getMessage();
        ErrorDTO message = new ErrorDTO(operationTime, HttpStatus.BAD_REQUEST.value()
                , resourceBundle.getString("entity_not_found")
                , error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}