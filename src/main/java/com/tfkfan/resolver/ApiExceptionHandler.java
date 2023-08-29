package com.tfkfan.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.tfkfan.exception.BusinessException;
import com.tfkfan.exception.ExceptionDictionary;
import com.tfkfan.service.dto.Error;
import com.tfkfan.service.dto.ValidationDetails;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.*;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    private final ObjectMapper json;

    @Autowired
    public ApiExceptionHandler(ObjectMapper json) {
        this.json = json;
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        return new ResponseEntity<>(new Error(ExceptionDictionary.VALIDATION_EXCEPTION.getErrorType().name(), ex.getMessage(),
            ex.getConstraintViolations()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new Error(ExceptionDictionary.VALIDATION_EXCEPTION.getErrorType().name(),
            ExceptionDictionary.VALIDATION_EXCEPTION.getMessage(),
            new ValidationDetails(mapBindErrors(ex.getBindingResult()))), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PSQLException.class})
    public ResponseEntity<?> handleConstraintViolation(PSQLException ex, WebRequest request) {
        return new ResponseEntity<>(new Error(ExceptionDictionary.VALIDATION_EXCEPTION.getErrorType().name(),
            ExceptionDictionary.VALIDATION_EXCEPTION.getMessage(),
            ex.getErrorCode()), HttpStatus.BAD_REQUEST);
    }

    //region Business errors
    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<?> handleBusinessException(BusinessException ex, WebRequest request) {
        Error error = makeError(ex);
        log.error(error.toString(), ex);

        HttpStatus httpStatus = getHttpStatus(ex);
        return new ResponseEntity<>(error, httpStatus);
    }

    //region Internal errors
    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleAnyException(Exception ex, WebRequest request) {
        Throwable root = ExceptionUtils.getRootCause(ex);
        Error error = new Error(root instanceof PSQLException ? ExceptionDictionary.DATABASE_EXCEPTION.getErrorType().name():
            ExceptionDictionary.INTERNAL_SERVER_EXCEPTION.getErrorType().name(),
            root.getMessage(),
            null);

        log.error(root.toString(), root);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static Error makeError(BusinessException ex) {
        return ex != null ? new Error(ex.getDictionary().getErrorType().name(), ex.getMessage(), ex.getDetails()) : null;
    }

    private static HttpStatus getHttpStatus(BusinessException ex) {
        return ex.getDictionary().getStatus();
    }

    private String mapBindErrors(BindingResult bindingResult) {
        Set<String> fields = new HashSet<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            fields.add(getJsonPropertyName(error.getField()));
        }
        return String.join(", ", fields);
    }

    private String getJsonPropertyName(String name) {
        PropertyNamingStrategy namingStrategy = json.getDeserializationConfig().getPropertyNamingStrategy();
        if (namingStrategy instanceof PropertyNamingStrategy.PropertyNamingStrategyBase) {
            return ((PropertyNamingStrategy.PropertyNamingStrategyBase) namingStrategy).translate(name);
        }
        return name;
    }
}
