
package com.redhat.app.configuration.connections;


import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

import com.redhat.app.configuration.ApplicationConfig;

/** Helper Class to map
 * Business Activities (Processes, Cases, DMN Services) to Business Project ALIAS
 * Business Project ALIAS to Service URL
 * 
 * Evolutions
 * 1 - Hardcoded (DONE)
 * 2 - Application Property Files/Config Maps
 * 3 - Database
 */
@Component
public class BusinessServiceMappingsHelper {

  private Map<String, String> businessActivityAliasMappings = new HashMap<String, String>();
  private Map<String, String> businessAliasServiceURLMappings = new HashMap<String, String>();

  @Value("${expenses.process.service.url}")
  private String expensesProcessServiceURL;

  @Value("${expenses.validation.service.url}")
  private String expensesValidationServiceURL;

//  @Autowired
//  private ApplicationConfig appConfig;


  private void createServiceMappingsHelper(){
    // Mappings of process definitions to ALIAS
    this.businessActivityAliasMappings.put("ExpensesApproval", "expenses-approvals-kjar");
    this.businessActivityAliasMappings.put("HR Department Limits Validation", "expenses-validations-kjar");
    this.businessActivityAliasMappings.put("IT Department Limits Validation", "expenses-validations-kjar");

    // Local Openshift Alias to Service URLs
//    this.businessAliasServiceURLMappings.put("expenses-approvals-kjar", appConfig.getProcessServiceUrl()+KIESERVERESTRATH);
//    this.businessAliasServiceURLMappings.put("expenses-validations-kjar", appConfig.getValidationServiceUrl()+KIESERVERESTRATH);
      this.businessAliasServiceURLMappings.put("expenses-approvals-kjar", expensesProcessServiceURL);
      this.businessAliasServiceURLMappings.put("expenses-validations-kjar", expensesValidationServiceURL);
  }

/*
  public BusinessServiceMappingsHelper(){
    // Mappings of process definitions to ALIAS
    this.businessActivityAliasMappings.put("ExpensesApproval", "expenses-approvals-kjar");
    this.businessActivityAliasMappings.put("HR Department Limits Validation", "expenses-validations-kjar");
    this.businessActivityAliasMappings.put("IT Department Limits Validation", "expenses-validations-kjar");

    // Local Openshift Alias to Service URLs
    this.businessAliasServiceURLMappings.put("expenses-approvals-kjar", "http://localhost:8092/rest/server");        
    this.businessAliasServiceURLMappings.put("expenses-validations-kjar", "http://localhost:8090/rest/server");  
  }

  public BusinessServiceMappingsHelper(boolean openshiftSettings){
    // Mappings of process definitions to ALIAS
    this.businessActivityAliasMappings.put("ExpensesApproval", "expenses-approvals-kjar");
    this.businessActivityAliasMappings.put("HR Department Limits Validation", "expenses-validations-kjar");
    this.businessActivityAliasMappings.put("IT Department Limits Validation", "expenses-validations-kjar");

    // Openshift Alias to Service URLs
    this.businessAliasServiceURLMappings.put("expenses-approvals-kjar", "http://hr-expenses-business-application-service:8090/rest/server");        
    this.businessAliasServiceURLMappings.put("expenses-validations-kjar", "http://hr-expenses-validations-business-service:8090/rest/server"); 
    //this.businessAliasServiceURLMappings.put("expenses-approvals-kjar", "http://localhost:8090/rest/server");        
    //this.businessAliasServiceURLMappings.put("expenses-validations-kjar", "http://localhost:8092/rest/server");   
}
  */    
   
   /**
    * Gets the businessActivityAliasMappings
    */
    public Map<String, String> getBusinessActivityAliasMappings() {
    createServiceMappingsHelper();
    return this.businessActivityAliasMappings;
   }
 
   /**
    * Sets the businessActivityAliasMappings
    */
  //  public void setBusinessActivityAliasMappings(Map<String, String> value) {
  //     this.businessActivityAliasMappings = value;
  //  }

   /**
    * Gets the businessAliasServiceURLMappings
    */
    public Map<String, String> getBusinessAliasServiceURLMappings() {
        createServiceMappingsHelper();
        return this.businessAliasServiceURLMappings;
   }
 
   /**
    * Sets the businessAliasServiceURLMappings
    */
  //  public void setBusinessAliasServiceURLMappings(Map<String, String> value) {
  //     this.businessAliasServiceURLMappings = value;
  //  }        


}
