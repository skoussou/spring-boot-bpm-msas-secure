package com.redhat.service.api.model.expenses;

import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


/**
 * Payload for TaskActionMessage a Task
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
public class TaskActionMessage implements TaskActionMessageIF,java.io.Serializable  {

  // The task details contain the container this Task is in so UI can return
  protected String taskContainerId = null;
  protected String actionUserId = null;
    // The task details contain the process Id this Task is created with
    protected String processId = null;

  public TaskActionMessage() {

  }

  public TaskActionMessage(String taskContainerId, String actionUserId, String processId) {
    this.taskContainerId = taskContainerId;
    this.actionUserId = actionUserId;
    this.processId = processId;
  }

   /**
    * Gets the TaskContainerId
    */
    public String getTaskContainerId() {
      System.out.println("Getting value of this.taskContainerId via get ["+this.taskContainerId+"]");
      if (this.taskContainerId == null){
        this.taskContainerId = "com.redhat:expenses-approvals-kjar:0.1.0";
      }
      return this.taskContainerId;
   }

    /**
    * Gets the ActionUserId
    */
    public String getActionUserId() {
      return this.actionUserId;
   }

    
    /**
    * Gets the ProcessId
    */
    public String getProcessId() {
      return this.processId;
   }

   @Override
   public String toString() {
       return "TaskActionMessage{" +
               "taskContainerId='" + taskContainerId + '\'' +
               ", actionUserId='" + actionUserId + '\'' +
               ", processId='" + processId + '\'' +
               '}';
   }

}

