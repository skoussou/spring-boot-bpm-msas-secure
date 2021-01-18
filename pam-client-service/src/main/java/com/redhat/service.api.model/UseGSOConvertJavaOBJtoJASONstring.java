package com.redhat.service.api.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

// import java.math.BigDecimal;
// import com.google.gson.Gson;


/**
  * activate dependecy GSON in pom.xml
  * add import of object here
  * create method
  * run mvn clean compile exec:java
  *
  */

public class UseGSOConvertJavaOBJtoJASONstring {
  
    public static void main(String args[]) { 

      //jsonExpenseApprovals();
      //jsonExpenseValidations();
      // jsonTaskCompletionMessage();
      // jsonTaskSearch1();
      // jsonTaskSearch2();
      // jsonTaskSearch3();      
    }

/*  private static void jsonExpenseApprovals(){

    List<com.redhat.hr.expenses.model.ExpenseItemModel> listOfExpenseItems = new ArrayList<com.redhat.hr.expenses.model.ExpenseItemModel>();
    ExpenseItemModel item1 = new ExpenseItemModel("Food", new BigDecimal(100000));
    ExpenseItemModel item2 = new ExpenseItemModel("Taxi", new BigDecimal(26000));
    ExpenseItemModel item3 = new ExpenseItemModel("Accommodation", new BigDecimal(10000));
    listOfExpenseItems.add(item1);
    listOfExpenseItems.add(item2);
    listOfExpenseItems.add(item3);
 
    ExpenseApprovals expenseApprovals = new ExpenseApprovals(listOfExpenseItems, "HR Manager", "HR", "PetraJones-35324", "expenseReport-60", 
    "nick.watkings@redhat.com", "NickWatkins-0253", "kie-server-user", "kie-server-user", "kie-server-user");

    Gson gson = new Gson();    
    String json = gson.toJson(expenseApprovals);
    System.out.println(json); 
 }*/

//    private static void jsonExpenseValidations(){

//     ExpenseValidations expenseValidation = new ExpenseValidations( "HR Manager", "HR", "Taxi", new BigDecimal(9999));

//     Gson gson = new Gson();    
//     String json = gson.toJson(expenseValidation);
//     System.out.println(json); 
//  }

// private static void jsonTaskCompletionMessage(){
// System.out.println("I am here");
//   TaskCompleteActionMessage taskInfo = new TaskCompleteActionMessage("com.redhat:expenses-approvals-kjar:0.6.0", 
//                              "user", "ExpensesApproval", "Approved", "I approve this report");

//        Gson gson = new Gson();    
//        String json = gson.toJson(taskInfo);
//        System.out.println(json);    
//     }

  //   private static void jsonTaskSearch1(){
  //     List<String> groupFilters = new ArrayList<String>();
  //     List<String> taskStatusfilters = new ArrayList<String>();
  //     String processId = "ExpensesApproval";
      
  //     TaskSearch search = new TaskSearch(processId, groupFilters, taskStatusfilters);

  //         Gson gson = new Gson();    
  //         String json = gson.toJson(search);
  //         System.out.println(json);    
  //     }

  // private static void jsonTaskSearch2(){
  //   List<String> groupFilters = new ArrayList<String>();
  //   List<String> taskStatusfilters = new ArrayList<String>();
  //   taskStatusfilters.add("InProgress");
  //   String processId = "ExpensesApproval";
          
  //   TaskSearch search = new TaskSearch(processId, groupFilters, taskStatusfilters);
    
  //   Gson gson = new Gson();    
  //   String json = gson.toJson(search);
  //   System.out.println(json);    
  // }

  // private static void jsonTaskSearch3(){
  //   List<String> groupFilters = new ArrayList<String>();
  //   groupFilters.add("financialapprover");
  //   List<String> taskStatusfilters = new ArrayList<String>();
  //   taskStatusfilters.add("InProgress");
  //   taskStatusfilters.add("Reserved");
  //   taskStatusfilters.add("Completed");

  //   String processId = "ExpensesApproval";
              
  //   TaskSearch search = new TaskSearch(processId, groupFilters, taskStatusfilters);
      
  //   Gson gson = new Gson();    
  //   String json = gson.toJson(search);
  //   System.out.println(json);    
  // }            
  
}
