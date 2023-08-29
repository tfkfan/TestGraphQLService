package com.tfkfan.exception;

import com.tfkfan.domain.Category;
import com.tfkfan.domain.CategoryProductModel;
import com.tfkfan.domain.ProductModel;

import java.util.Map;

/**
 * @author Baltser Artem tfkfan
 */
public class EntityNotFoundException extends EntityTypeDefinedException {
    static final ExceptionDictionary dict = ExceptionDictionary.ENTITY_NOT_FOUND_EXCEPTION;

    public EntityNotFoundException(Map<String, Object> details, String entityType) {
        super(dict.getCode(), details, entityType);
    }

    public EntityNotFoundException(String message, Map<String, Object> details, String entityType) {
        super(message, dict.getCode(), details, entityType);
    }

    public EntityNotFoundException(String message, Throwable cause, Map<String, Object> details, String entityType) {
        super(message, cause, dict.getCode(), details, entityType);
    }

    public EntityNotFoundException(Throwable cause, Map<String, Object> details, String entityType) {
        super(cause, dict.getCode(), details, entityType);
    }

    public EntityNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace, Map<String, Object> details, String entityType) {
        super(message, cause, enableSuppression, writableStackTrace, dict.getCode(), details, entityType);
    }

    public static EntityNotFoundException buildForParentCategory(String parentCategoryCode) {
        return new EntityNotFoundException(String.format("Родительская категория с кодом %s не найдена", parentCategoryCode),
            Map.of("parentCategoryCode", parentCategoryCode), Category.ENTITY_NAME);
    }

    public static EntityNotFoundException buildForCategory(String code) {
        return new EntityNotFoundException(String.format("Категория с кодом %s не найдена", code),
            Map.of("code", code), Category.ENTITY_NAME);
    }

    public static EntityNotFoundException buildForModel(String code) {
        return new EntityNotFoundException(String.format("Модель с кодом %s не найдена", code),
            Map.of("code", code), ProductModel.ENTITY_NAME);
    }

    public static EntityNotFoundException buildForCategoryModel(String categoryCode, String modelCode) {
        return new EntityNotFoundException(String.format("Связь категории %s с моделью %s не найдена",
            categoryCode, modelCode), Map.of("categoryCode", categoryCode, "modelCode", modelCode), CategoryProductModel.ENTITY_NAME);
    }
}
