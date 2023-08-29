package com.tfkfan.exception;

import java.util.Map;

/**
 * @author Baltser Artem tfkfan
 */
public abstract class EntityTypeDefinedException extends BusinessException{
    private final String entityType;

    public EntityTypeDefinedException(String code, Map<String, Object> details, String entityType) {
        super(code, details);
        this.entityType = entityType;
    }

    public EntityTypeDefinedException(String message, String code, Map<String, Object> details, String entityType) {
        super(message, code, details);
        this.entityType = entityType;
    }

    public EntityTypeDefinedException(String message, Throwable cause, String code, Map<String, Object> details, String entityType) {
        super(message, cause, code, details);
        this.entityType = entityType;
    }

    public EntityTypeDefinedException(Throwable cause, String code, Map<String, Object> details, String entityType) {
        super(cause, code, details);
        this.entityType = entityType;
    }

    public EntityTypeDefinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code,
                                      Map<String, Object> details, String entityType) {
        super(message, cause, enableSuppression, writableStackTrace, code, details);
        this.entityType = entityType;
    }

    public String getEntityType() {
        return entityType;
    }
}
