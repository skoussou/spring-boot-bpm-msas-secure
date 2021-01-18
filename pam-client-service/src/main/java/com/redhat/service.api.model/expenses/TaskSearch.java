package com.redhat.service.api.model.expenses;

import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


/**
 * Payload for Completing a Task
 * 
 * "{  \"tApprovalStatus\": \"Approvedr\",    
 *     \"tApprovalComment\": \"I approve this task\" 
 * }"
 * 
 * "{  \"tApprovalStatus\": \"Rejected\",    
 *     \"tApprovalComment\": \"Task is rejected\" 
 * }"
 * 
 * "{  \"tApprovalStatus\": \"Revision_Request\",    
 *     \"tApprovalComment\": \"Revise ITem 4 in expenses form\" 
 * }"
 * 
 */
public class TaskSearch implements java.io.Serializable {

  // optional (if not provided client will search ALL connected/known services for ALIAS and do the task search)
  private String processId = null;
  // optional (if not provided client will only return tasks owned by user)
  private List<String> groupFilters = new ArrayList<String>();

  // should be present if not initialize to "Reserved" and can be (Reserved, Ready, Completed) to support
  // searching for open tasks (in my groups)
  // searching for my own reserved tasks
  // searching for my own completed
  private List<String> taskStatusfilters = new ArrayList<String>(){ 
      { 
          add("Reserved"); 
      } }; 

  public TaskSearch() {

  }


  public TaskSearch(String processId, List<String> groupFilters, List<String> taskStatusfilters) {
    this.processId = processId;
    this.groupFilters = groupFilters;
    this.taskStatusfilters = taskStatusfilters;
  }

  public TaskSearch(String processId) {
    this.processId = processId;
  }  

  public TaskSearch(String processId, List<String> groupFilters) {
    this.processId = processId;
    this.groupFilters = groupFilters;
  }


   /**
    * Gets the processId
    */
    public String getProcessId() {
      return this.processId;
   }

      /**
    * Gets the TaskStatusfilters
    */
    public List<String>  getTaskStatusfilters() {
      return this.taskStatusfilters;
   }
 
 
 
      /**
    * Gets the GroupFilters
    */
    public List<String>  getGroupFilters() {
      return this.groupFilters;
   }
 

   @Override
   public String toString() {
       return "TaskSearch{" +
               "processId='" + processId + '\'' +
               ", groupFilters=" + groupFilters + " "+
               ", taskStatusfilters=" + taskStatusfilters + " "+
               '}';
   }

}

