package com.tfkfan.shared;

import graphql.ErrorClassification;

/**
 * @author Baltser Artem tfkfan
 */
public enum ErrorType implements ErrorClassification {
    EntitySubordinationException, EntityNotFoundException,
    DatabaseException, EntityAlreadyExistsException, ValidationException,
    InternalServerException;

    private ErrorType() {
    }
}
