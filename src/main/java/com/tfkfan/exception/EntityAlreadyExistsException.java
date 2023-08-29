package com.tfkfan.exception;

import com.tfkfan.domain.Category;
import com.tfkfan.domain.CategoryProductModel;
import com.tfkfan.domain.ProductModel;

import java.util.Map;

/**
 * @author Baltser Artem tfkfan
 */
public class EntityAlreadyExistsException extends EntityTypeDefinedException{
    static final ExceptionDictionary dict = ExceptionDictionary.ENTITY_ALREADY_EXISTS_EXCEPTION;

    public EntityAlreadyExistsException(Map<String, Object> details, String entityType) {
        super(dict.getCode(), details, entityType);
    }

    public EntityAlreadyExistsException(String message, Map<String, Object> details, String entityType) {
        super(message, dict.getCode(), details, entityType);
    }

    public EntityAlreadyExistsException(String message, Throwable cause, Map<String, Object> details, String entityType) {
        super(message, cause, dict.getCode(), details, entityType);
    }

    public EntityAlreadyExistsException(Throwable cause, Map<String, Object> details, String entityType) {
        super(cause, dict.getCode(), details, entityType);
    }

    public EntityAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace, Map<String, Object> details, String entityType) {
        super(message, cause, enableSuppression, writableStackTrace, dict.getCode(), details, entityType);
    }

    public static EntityAlreadyExistsException buildForCategory(String code){
        return new EntityAlreadyExistsException(String.format("Категория с кодом %s уже существует",code),
            Map.of("code", code), Category.ENTITY_NAME);
    }

    public static EntityAlreadyExistsException buildForModel(String code){
        return new EntityAlreadyExistsException(String.format("Модель с кодом %s уже существует", code),
            Map.of("code", code), ProductModel.ENTITY_NAME);
    }

    public static EntityAlreadyExistsException buildForCategoryModel(String categoryCode, String modelCode){
        return new EntityAlreadyExistsException(String.format("Связь категории %s с моделью %s уже существует",
            categoryCode, modelCode), Map.of("categoryCode", categoryCode, "modelCode", modelCode), CategoryProductModel.ENTITY_NAME);
    }
}
