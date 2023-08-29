package com.tfkfan.exception;

import com.tfkfan.shared.ErrorType;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Baltser Artem tfkfan
 */
public abstract class BusinessException extends RuntimeException implements GraphQLError {
    private final String code;
    private final ExceptionDictionary dictionary;
    private final Map<String, Object> details;

    public BusinessException(String code, Map<String, Object> details) {
        this.code = code;
        this.details = details;
        this.dictionary = ExceptionDictionary.getDictByCode(code);
    }

    public BusinessException(String message, String code, Map<String, Object> details) {
        super(message);
        this.code = code;
        this.details = details;
        this.dictionary = ExceptionDictionary.getDictByCode(code);
    }

    public BusinessException(String message, Throwable cause, String code, Map<String, Object> details) {
        super(message, cause);
        this.code = code;
        this.details = details;
        this.dictionary = ExceptionDictionary.getDictByCode(code);
    }

    public BusinessException(Throwable cause, String code, Map<String, Object> details) {
        super(cause);
        this.code = code;
        this.details = details;
        this.dictionary = ExceptionDictionary.getDictByCode(code);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, Map<String, Object> details) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.details = details;
        this.dictionary = ExceptionDictionary.getDictByCode(code);
    }

    public String getCode() {
        return code;
    }

    public ExceptionDictionary getDictionary() {
        return dictionary;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return dictionary.getErrorType();
    }

    @Override
    public Map<String, Object> getExtensions() {
        return details;
    }
}
