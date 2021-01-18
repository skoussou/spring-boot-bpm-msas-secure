package com.redhat.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class BusinessException extends RuntimeException {

    private String businessCorrelationId = "";

    private ArrayList<String> errorsList = new ArrayList<String>();

    private HashMap<String, Object> errorInformation = new HashMap<String, Object>();


    public BusinessException(Throwable cause, String message) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(Throwable cause, String message, String businessCorrelationId) {
        super(message, cause);
        this.businessCorrelationId = businessCorrelationId;
    }

    public BusinessException(Throwable cause, String message, String businessCorrelationId, ArrayList<String> errorsList) {
        super(message, cause);
        this.businessCorrelationId = businessCorrelationId;
        this.errorsList = errorsList;
    }

    public BusinessException(Throwable cause, String message, String businessCorrelationId, HashMap<String, Object> errorInformation) {
        super(message, cause);
        this.businessCorrelationId = businessCorrelationId;
        this.errorInformation = errorInformation;
    }

    public void setErrorInformation( String informationName, Object information ) {
        this.errorInformation.put(informationName, information);
    }

    public Map<String, Object> getInformationMap() {
        return Collections.unmodifiableMap(this.errorInformation);
    }

    public String toString() { 
      return "BusinessException: 'Unique Correlation ID: " + this.businessCorrelationId + "', Error Cause: '" 
                 + super.getMessage() + "', errorsList: '" + this.errorsList + "', errorInformation: '" + this.errorInformation + "'";
    }

}
