package com.redhat.service.api.model.expenses;

import java.io.Serializable;
import com.redhat.hr.expenses.model.ExpenseItemModel;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

import java.math.BigDecimal;


/**
 * Payload for Starting ExpensesApprovals process
 */
public class ExpenseApprovals implements java.io.Serializable {

  //"{  \"pExpensesItemsTypesHighestCost\": [
  //           {\"com.redhat.hr.expenses.model.ExpenseItemModel\": {\"expenseType\" : \"Food\", \"expenseValue\" :  100000}}, 
  //           {\"com.redhat.hr.expenses.model.ExpenseItemModel\": {\"expenseType\" : \"Taxi\", \"expenseValue\" :  26000}}, 
  //           {\"com.redhat.hr.expenses.model.ExpenseItemModel\": {\"expenseType\" : \"Accommodation\", \"expenseValue\" :  10000}}],    
  //    \"pDepartmentRole\": \"HR Manager\",    
  //    \"pDepartmentName\": \"HR\",    
  //    \"pExpenseCreatorID\" : \"stelios\",    
  //    \"pExpenseFormCorrelationKey\" : \"expenseForm-050\",     
  //    \"pExpenseOwnerID\" : \"stelios\",    
  //    \"pExpenseOwnerNotificationAddress\" : \"stelios@redhat.com\",    
  //    \"pFinancialAffairsDirector\" : \"pamAdmin\",    
  //    \"pFirstApprover\" : \"pamAdmin\",    
  //    \"pSecondApprover\" : \"pamAdmin\",    
  //    \"pCauseBusinessError\" : true}"

  private List<com.redhat.hr.expenses.model.ExpenseItemModel> listOfExpenseItems = new ArrayList<com.redhat.hr.expenses.model.ExpenseItemModel>();
  private String departmentRole = null;
  private String departmentName = null;
  private String expenseSubmitterID = null;
  private String expenseFormCorrelationKey = null;
  private String expenseOwnerID = null;
  private String expenseOwnerNotificationAddress = null;
  private String financialAffairsDirectorApproverUsername = null;
  private String firstApproverUsername = null;
  private String secondApproverUsername = null;

  public ExpenseApprovals() {

  }

  public ExpenseApprovals(List<com.redhat.hr.expenses.model.ExpenseItemModel> listOfExpenseItems,
                        String departmentRole, String departmentName, String expenseSubmitterID,
                        String expenseFormCorrelationKey, String expenseOwnerNotificationAddress, 
                        String expenseOwnerID, String financialAffairsDirectorApproverUsername,  
                        String firstApproverUsername, String secondApproverUsername) {
    this.listOfExpenseItems = listOfExpenseItems;
    this.departmentRole = departmentRole;
    this.expenseSubmitterID = expenseSubmitterID;
    this.expenseFormCorrelationKey = expenseFormCorrelationKey;
    this.expenseOwnerNotificationAddress = expenseOwnerNotificationAddress;
    this.expenseOwnerID = expenseOwnerID;
    this.financialAffairsDirectorApproverUsername = financialAffairsDirectorApproverUsername;
    this.firstApproverUsername = firstApproverUsername;
    this.secondApproverUsername = secondApproverUsername;
  }

   /**
    * Gets the ExpenseFormCorrelationKey
    */
    public String getExpenseFormCorrelationKey() {
      return this.expenseFormCorrelationKey;
   }
 
   /**
    * Sets the ExpenseFormCorrelationKey
    */
   // public void setExpenseFormCorrelationKey(String value) {
   //    this.expenseFormCorrelationKey = value;
   // }

     /**
    * Gets the listOfExpenseItems
    */
    public List<com.redhat.hr.expenses.model.ExpenseItemModel> getListOfExpenseItems() {
      return this.listOfExpenseItems;
   }
 
   /**
    * Sets the listOfExpenseItems
    */
   // public void setListOfExpenseItems(List<com.redhat.hr.expenses.model.ExpenseItemModel> value) {
   //    this.listOfExpenseItems = value;
   // }
 
   /**
    * Gets the departmentRole
    */
   public String getDepartmentRole() {
      return this.departmentRole;
   }
 
   /**
    * Sets the departmentRole
    */
   // public void setDepartmentRole(String value) {
   //    this.departmentRole = value;
   // }
 
   /**
    * Gets the departmentName
    */
   public String getDepartmentName() {
      return this.departmentName;
   }
 
   /**
    * Sets the departmentName
    */
   // public void setDepartmentName(String value) {
   //    this.departmentName = value;
   // }
 
   /**
    * Gets the expenseSubmitterID
    */
   public String getExpenseSubmitterID() {
      return this.expenseSubmitterID;
   }
 
   /**
    * Sets the expenseSubmitterID
    */
   // public void setExpenseSubmitterID(String value) {
   //    this.expenseSubmitterID = value;
   // }
 
   /**
    * Gets the expenseOwnerNotificationAddress
    */
   public String getExpenseOwnerNotificationAddress() {
      return this.expenseOwnerNotificationAddress;
   }
 
   /**
    * Sets the expenseOwnerNotificationAddress
    */
   // public void setExpenseOwnerNotificationAddress(String value) {
   //    this.expenseOwnerNotificationAddress = value;
   // }

   /**
    * Gets the expenseOwnerID
    */
    public String getExpenseOwnerID() {
      return this.expenseOwnerID;
   }
 
   /**
    * Sets the expenseOwnerID
    */
   // public void setExpenseOwnerID(String value) {
   //    this.expenseOwnerID = value;
   // }   
 
   /**
    * Gets the financialAffairsDirectorApproverUsername
    */
   public String getFinancialAffairsDirectorApproverUsername() {
      return this.financialAffairsDirectorApproverUsername;
   }
 
   /**
    * Sets the financialAffairsDirectorApproverUsername
    */
   // public void setFinancialAffairsDirectorApproverUsername(String value) {
   //    this.financialAffairsDirectorApproverUsername = value;
   // }
 
   /**
    * Gets the firstApproverUsername
    */
   public String getFirstApproverUsername() {
      return this.firstApproverUsername;
   }
 
   /**
    * Sets the firstApproverUsername
    */
   // public void setFirstApproverUsername(String value) {
   //    this.firstApproverUsername = value;
   // }
 
   /**
    * Gets the secondApproverUsername
    */
   public String getSecondApproverUsername() {
      return this.secondApproverUsername;
   }
 
   /**
    * Sets the secondApproverUsername
    */
   // public void setSecondApproverUsername(String value) {
   //    this.secondApproverUsername = value;
   // }

   @Override
   public String toString() {
       return "ExpenseApprovals{" +
               "listOfExpenseItems='" + listOfExpenseItems + '\'' +
               ", departmentRole=" + departmentRole +
               "departmentName='" + departmentName + '\'' +
               ", expenseSubmitterID=" + expenseSubmitterID +
               "expenseFormCorrelationKey='" + expenseFormCorrelationKey + '\'' +
               "expenseOwnerNotificationAddress='" + expenseOwnerNotificationAddress + '\'' +
               ", financialAffairsDirectorApproverUsername=" + financialAffairsDirectorApproverUsername +
               ", expenseOwnerID=" + expenseOwnerID +
               "firstApproverUsername='" + firstApproverUsername + '\'' +
               ", secondApproverUsername=" + secondApproverUsername +            
               '}';
   }

   public Map<String, Object> processVarsMapper (){
     Map<String, Object> processVars = new HashMap<String, Object>();
     if (listOfExpenseItems != null)  processVars.put("pExpensesItemsTypesHighestCost", listOfExpenseItems);
     if (departmentRole != null) processVars.put("pDepartmentRole", departmentRole);
     if (departmentName != null) processVars.put("pDepartmentName", departmentName);
     if (expenseSubmitterID != null) processVars.put("pExpenseCreatorID",expenseSubmitterID);
     if (expenseFormCorrelationKey != null) processVars.put("pExpenseFormCorrelationKey", expenseFormCorrelationKey);
     if (expenseOwnerID != null) processVars.put("pExpenseOwnerID", expenseOwnerID);
     if (expenseOwnerNotificationAddress != null) processVars.put("pExpenseOwnerNotificationAddress", expenseOwnerNotificationAddress);
     if (financialAffairsDirectorApproverUsername != null) processVars.put("pFinancialAffairsDirector", financialAffairsDirectorApproverUsername);
     if (firstApproverUsername != null) processVars.put("pFirstApprover", firstApproverUsername);
     if (secondApproverUsername != null) processVars.put("pSecondApprover", secondApproverUsername);

     return processVars;
   }

   // Method as in BPMN notation this becomes a linkelistHashMap without any indication of the orginal object stored so casting fails in 
   // DepartmentLimitsValidation multi instace
   /*private ArrayList<String> collectionToJsonArrayWithType(List<ExpenseItemModel> items){

      ArrayList<String> newitemsList = new ArrayList<String>();
      for(ExpenseItemModel item : items){
         //[ExpenseItemModel: ' expenseType: Food', expenseValue: '100000'
         String newItemFormat = "ExpenseItemModel: ' "+item.getExpenseType()+"', expenseValue: '"+item.getExpenseValue()+"'";
         newitemsList.add(newItemFormat);
      }

      System.out.println("\t  NEW ARRAY CREATED ["+newitemsList+"]");
      return newitemsList;
   }*/

}

