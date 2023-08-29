package com.tfkfan.exception;

import com.tfkfan.domain.Category;

import java.util.Map;

/**
 * @author Baltser Artem tfkfan
 */
public class EntitySubordinationException extends EntityTypeDefinedException  {
    static final ExceptionDictionary dict = ExceptionDictionary.ENTITY_SUBORDINATION_EXCEPTION;

    public EntitySubordinationException( Map<String, Object> details, String entityType) {
        super(dict.getCode(), details, entityType);
    }

    public EntitySubordinationException(String message,Map<String, Object> details, String entityType) {
        super(message, dict.getCode(), details, entityType);
    }

    public EntitySubordinationException(String message, Throwable cause, Map<String, Object> details, String entityType) {
        super(message, cause, dict.getCode(), details, entityType);
    }

    public EntitySubordinationException(Throwable cause, Map<String, Object> details, String entityType) {
        super(cause, dict.getCode(), details, entityType);
    }

    public EntitySubordinationException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace,  Map<String, Object> details, String entityType) {
        super(message, cause, enableSuppression, writableStackTrace, dict.getCode(), details, entityType);
    }

    public static EntitySubordinationException buildForCategory(Map<String,Object> details){
        return new EntitySubordinationException("Категория не может являться родительской по отношению к самой себе",
            details, Category.ENTITY_NAME);
    }
}
