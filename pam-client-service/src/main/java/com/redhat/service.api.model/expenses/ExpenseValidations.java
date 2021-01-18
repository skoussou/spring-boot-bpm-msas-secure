package com.redhat.service.api.model.expenses;

import java.io.Serializable;
import com.redhat.hr.expenses.model.ExpenseItemModel;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;


/**
 * Payload for Starting Validation of Limits DMN Service
  *         
 * {
 *  "model-namespace" : "https://redhat.com/dmn/hrdepartmentlimitsvalidationUUID",
 *  "model-name" : "HR Department Limits Validation",
 *  "dmn-context" : {"Role" : "HR Manager", "Expense Type": "Taxi", "Limit" : 9999}
 *  }
 *
 */
public class ExpenseValidations implements java.io.Serializable {

  private String departmentName = null;
  private String departmentRole = null;
  private String expenseType = null;
  private BigDecimal limit = null;

  //String containerId = "expenses-validations-kjar";
  private String namespace = null; //"https://redhat.com/dmn/hrdepartmentlimitsvalidationUUID";
  private String model = null; //"HR Department Limits Validation";
  private String decision = null; //"HR Department Limits Validation";

  private final String MODEL_SUFFIX = " Department Limits Validation";

  public ExpenseValidations(String departmentRole, String departmentName, String expenseType,
  BigDecimal limit) {
    this.departmentRole = departmentRole;
    this.departmentName = departmentName;
    this.expenseType = expenseType;
    this.limit = limit;
    this.namespace = "https://redhat.com/dmn/"+departmentName.toLowerCase()+"departmentlimitsvalidationUUID";
    this.model = departmentName + MODEL_SUFFIX;
    this.decision = departmentName + MODEL_SUFFIX;
  }

     /**
    * Gets the DepartmentRole
    */
    public String getDepartmentRole() {
      return this.departmentRole;
   }

      /**
    * Gets the departmentName
    */
    public String getDepartmentName() {
      return this.departmentName;
   }

      /**
    * Gets the ExpenseType
    */
    public String getExpenseType() {
      return this.expenseType;
   }

      /**
    * Gets the Limit
    */
    public BigDecimal getLimit() {
      return this.limit;
   }

      /**
    * Gets the Namespace
    */
    public String getNamespace() {
      return this.namespace;
   }

      /**
    * Gets the Model
    */
    public String getModel() {
      return this.model;
   }

      /**
    * Gets the Decision
    */
    public String getDecision() {
      return this.decision;
   }

   @Override
   public String toString() {
       return "ExpenseValidations{" +
               "departmentRole='" + departmentRole + '\'' +
               ", departmentName='" + departmentName + '\'' +
               ", expenseType=" + expenseType +
               ", limit='" + limit + '\'' +
               ", namespace='" + namespace + '\'' +
               ", model=" + model +
               ", decision=" + decision +       
               '}';
   }

   public Map<String, Object> dmnContextVarsMapper (){
    Map <String, Object> parameters = new HashMap<String, Object>();
    if (this.departmentRole != null)  parameters.put("Role", this.departmentRole);
    if (this.expenseType != null) parameters.put("Expense Type", this.expenseType);
    if (this.limit != null) parameters.put("Limit", this.limit);
 
    return parameters;
  }

}
