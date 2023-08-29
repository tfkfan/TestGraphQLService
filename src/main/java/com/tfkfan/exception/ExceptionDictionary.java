package com.tfkfan.exception;

import com.tfkfan.shared.ErrorType;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

/**
 * @author Baltser Artem tfkfan
 */
public enum ExceptionDictionary {
    ENTITY_SUBORDINATION_EXCEPTION("CODE_1",
        "DEFAULT MSG 1", HttpStatus.BAD_REQUEST, ErrorType.EntitySubordinationException),
    ENTITY_NOT_FOUND_EXCEPTION("CODE_2",
        "DEFAULT MSG 2", HttpStatus.NOT_FOUND, ErrorType.EntityNotFoundException),
    DATABASE_EXCEPTION("CODE_3",
        "DEFAULT MSG 3", HttpStatus.INTERNAL_SERVER_ERROR, ErrorType.DatabaseException),
    ENTITY_ALREADY_EXISTS_EXCEPTION("CODE_4",
        "DEFAULT_MSG 4", HttpStatus.CONFLICT, ErrorType.EntityAlreadyExistsException),
    VALIDATION_EXCEPTION("CODE_5",
        "Не заполнены обязательные поля", HttpStatus.BAD_REQUEST, ErrorType.ValidationException),
    INTERNAL_SERVER_EXCEPTION("CODE_6",
        "DEFAULT MSG 6", HttpStatus.INTERNAL_SERVER_ERROR, ErrorType.InternalServerException);

    private String code;
    private String message;
    private HttpStatus status;
    private ErrorType errorType;

    ExceptionDictionary(String code, String message, HttpStatus status, ErrorType errorType) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.errorType = errorType;
    }

    public static ExceptionDictionary getDictByCode(String code) {
        return Arrays
            .stream(values())
            .filter(e -> e.code.equals(code))
            .findFirst()
            .orElse(ExceptionDictionary.INTERNAL_SERVER_EXCEPTION);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
