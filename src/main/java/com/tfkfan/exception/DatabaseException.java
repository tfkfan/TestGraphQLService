package com.tfkfan.exception;


import java.util.Map;

/**
 * @author Baltser Artem tfkfan
 */
public class DatabaseException extends ScenarioException{
    static final ExceptionDictionary dict = ExceptionDictionary.DATABASE_EXCEPTION;

    public DatabaseException(Map<String, Object> details, String scenario) {
        super(dict.getCode(), details, scenario);
    }

    public DatabaseException(String message, Map<String, Object> details, String scenario) {
        super(message, dict.getCode(), details, scenario);
    }

    public DatabaseException(String message, Throwable cause, Map<String, Object> details, String scenario) {
        super(message, cause, dict.getCode(), details, scenario);
    }

    public DatabaseException(Throwable cause, Map<String, Object> details, String scenario) {
        super(cause, dict.getCode(), details, scenario);
    }

    public DatabaseException(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace, Map<String, Object> details, String scenario) {
        super(message, cause, enableSuppression, writableStackTrace, dict.getCode(), details, scenario);
    }
}
