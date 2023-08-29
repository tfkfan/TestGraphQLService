package com.tfkfan.exception;

import java.util.Map;

/**
 * @author Baltser Artem tfkfan
 */
public abstract class ScenarioException extends BusinessException{
    private final String scenario;

    public ScenarioException(String code, Map<String, Object> details, String scenario) {
        super(code, details);
        this.scenario = scenario;
    }

    public ScenarioException(String message, String code, Map<String, Object> details, String scenario) {
        super(message, code, details);
        this.scenario = scenario;
    }

    public ScenarioException(String message, Throwable cause, String code, Map<String, Object> details, String scenario) {
        super(message, cause, code, details);
        this.scenario = scenario;
    }

    public ScenarioException(Throwable cause, String code, Map<String, Object> details, String scenario) {
        super(cause, code, details);
        this.scenario = scenario;
    }

    public ScenarioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, Map<String, Object> details, String scenario) {
        super(message, cause, enableSuppression, writableStackTrace, code, details);
        this.scenario = scenario;
    }

    public String getScenario() {
        return scenario;
    }
}
