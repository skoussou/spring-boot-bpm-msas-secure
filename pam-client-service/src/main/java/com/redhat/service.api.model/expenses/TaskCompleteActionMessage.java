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
public class TaskCompleteActionMessage extends TaskActionMessage implements TaskActionMessageIF,java.io.Serializable {

  private String approvalStatus = null;
  private String approvalMessage = null;

  public TaskCompleteActionMessage() {

  }

  public TaskCompleteActionMessage(String approvalStatus, String approvalMessage) {
    this.approvalStatus = approvalStatus;
    this.approvalMessage = approvalMessage;
  }

  public TaskCompleteActionMessage(String taskContainerId, String actionUserId, String processId, String approvalStatus, String approvalMessage) {
    super.taskContainerId = taskContainerId;
    super.actionUserId = actionUserId;
    super.processId = processId;
    this.approvalStatus = approvalStatus;
    this.approvalMessage = approvalMessage;
  }


   /**
    * Gets the ApprovalStatus
    */
    public String getApprovalStatus() {
      return this.approvalStatus;
   }
 
 
   /**
    * Gets the ApprovalMessage
    */
   public String getApprovalMessage() {
      return this.approvalMessage;
   }
 

   @Override
   public String toString() {
       return "TaskCompleteActionMessage{" +
               "approvalStatus='" + approvalStatus + '\'' +
               ", approvalMessage=" + approvalMessage + " "+
               super.toString() +
               '}';
   }

   public Map<String, Object> taskVarsMapper(){
     Map<String, Object> taskVars = new HashMap<String, Object>();
     if (approvalStatus != null)  taskVars.put("tApprovalStatus", approvalStatus);
     if (approvalMessage != null) taskVars.put("tApprovalComment", approvalMessage);
    
     return taskVars;
   }

}

