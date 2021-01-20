package com.redhat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.context.annotation.Profile;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.redhat.app.configuration.connections.AbstractKieServerConnector;
import com.redhat.service.api.model.expenses.ExpenseApprovals;
import com.redhat.service.api.model.expenses.ExpenseValidations;
import com.redhat.app.configuration.connections.BusinessServiceMappingsHelper;
import com.redhat.service.api.model.expenses.ExpenseValidationResponse;
import com.redhat.service.api.model.expenses.TaskCompleteActionMessage;
import com.redhat.service.api.model.expenses.TaskSearch;

import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNResult;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.api.model.instance.ProcessInstance;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerResourceList;
import org.kie.server.api.model.instance.TaskSummary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Profile("localnonsso")
@RestController
@RequestMapping("/rhpam")
public class NonSSORHPAMController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NonSSORHPAMController.class);

    @Autowired
    private BusinessServiceMappingsHelper helper;

    private AbstractKieServerConnector createBusinessSvcConnector(String businessActivity) {

        // Gets Openshift Helper
        //BusinessServiceMappingsHelper helper = new BusinessServiceMappingsHelper(true);

        LOGGER.debug("Connector with Service URL ["+helper.getBusinessAliasServiceURLMappings().get(helper.getBusinessActivityAliasMappings().get(businessActivity))+"]");
        
        AbstractKieServerConnector connector = new AbstractKieServerConnector(helper.getBusinessAliasServiceURLMappings().get(helper.getBusinessActivityAliasMappings().get(businessActivity)));

        return connector;
    }

    @RolesAllowed("kie-server")
    @PostMapping(value = "/process/{processId}")
	@ResponseBody
    public Long startProcess(@PathVariable("processId") String processId,  
                             @RequestBody ExpenseApprovals body) {

        LOGGER.debug("================ Process Service (API: startProcess called =================");
        LOGGER.debug("startProcess["+processId+"]");
        LOGGER.debug("Payload ["+body.toString()+"]");

        AbstractKieServerConnector connector = createBusinessSvcConnector(processId);

        // TODO 
        LOGGER.debug("Our job is now to read a Payload class and find a mapper to get its type for the process and map the payload");
        LOGGER.debug("Our job is then to map the process to a service (SQL for this?) ");
        LOGGER.debug("--------------------------------------------------------------------------------------");

        // Gets Openshift Helper
        //BusinessServiceMappingsHelper helper = new BusinessServiceMappingsHelper(true);
        Long id = connector.getProcessClient().startProcess(helper.getBusinessActivityAliasMappings().get(processId), processId, body.processVarsMapper());

        LOGGER.debug("Created Business Process ["+processId+"] with ID ["+id+"] ");

        return id;
    }

    @RolesAllowed("kie-server")
    @PostMapping(value = "/expenses/department/validation")
	@ResponseBody
    public ResponseEntity<ExpenseValidationResponse> expenseValidation(@RequestBody ExpenseValidations body) {
        
        LOGGER.debug("========================= DMN Service (API: expenseValidation called =================");
        LOGGER.debug("Body of Request ["+body+"]");

        AbstractKieServerConnector connector = createBusinessSvcConnector(body.getDecision());

        // TODO 
        LOGGER.debug("Our job is now to read a Payload class (departmentName, departmentRole, item) and find a mapper to get its type for the process and map the payload");
        LOGGER.debug("Our job is then to map the DMN Service to a service (SQL for this?) ");
        LOGGER.debug("--------------------------------------------------------------------------------------");

        // Gets Openshift Helper
        //BusinessServiceMappingsHelper helper = new BusinessServiceMappingsHelper(true);

        DMNContext dmnContext = connector.getDMNClient().newContext();

        for (Map.Entry<String, Object> entry : body.dmnContextVarsMapper().entrySet()) {
            dmnContext.set(entry.getKey(), entry.getValue());
        }

        LOGGER.debug("DMN Context REQUEST: ["+dmnContext+"]");

        ServiceResponse<DMNResult> evaluationResult = null;

        helper.getBusinessActivityAliasMappings().get(body);
        if (body.getDecision() != null) {
            evaluationResult = connector.getDMNClient().evaluateDecisionByName(
                helper.getBusinessActivityAliasMappings().get(body.getDecision()), 
                body.getNamespace(), body.getModel(), body.getDecision(), dmnContext);
        } else {
            evaluationResult = connector.getDMNClient().evaluateAll(helper.getBusinessActivityAliasMappings().get(body.getDecision()), 
            body.getNamespace(), body.getModel(), dmnContext);
        }

        DMNResult dmnResult = evaluationResult.getResult();

        LOGGER.debug("DMN Service ["+helper.getBusinessActivityAliasMappings().get(body.getDecision())+"] Decision ["+body.getDecision()+"] called ");
        LOGGER.debug("DMN Results ["+dmnResult+"]");

        //return "DMN Service called VALIDATION ["+dmnResult.getContext().get(body.getDecision())+"]";
        return ResponseEntity.ok(new ExpenseValidationResponse((String)dmnResult.getContext().get(body.getDecision())));
    }    

    @RolesAllowed("kie-server")
    @RequestMapping(value = "/expenses/instances/{processId}", method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<List<ProcessInstance>> processInstances(@PathVariable("processId") String processId) {
        
        LOGGER.debug("========================= Search (API: processInstances called =================");
        AbstractKieServerConnector connector = createBusinessSvcConnector(processId);
        //BusinessServiceMappingsHelper helper = new BusinessServiceMappingsHelper(true);


        // TODO 
        LOGGER.debug("Our job is now to search for instances of ["+processId+"]");
        LOGGER.debug("Our job is then to map the  ["+processId+"] Process to Service URL ["+helper.getBusinessAliasServiceURLMappings().get(helper.getBusinessActivityAliasMappings().get(processId))+"] (SQL or ConfigMap for this eventually?) ");
        LOGGER.debug("--------------------------------------------------------------------------------------");
    
        ServiceResponse<KieContainerResourceList> response = connector.getServicesClient().listContainers();
        
        List<ProcessInstance> instances = new ArrayList<ProcessInstance>();
        LOGGER.debug("--------------- List of Containers in Service URL ["+helper.getBusinessAliasServiceURLMappings().get(helper.getBusinessActivityAliasMappings().get(processId))+"] ------------------");

        for(KieContainerResource container :  ((KieContainerResourceList)response.getResult()).getContainers()) {
            LOGGER.debug("\t - Container ID ["+ container.getContainerId()+"] alias ["+container.getContainerAlias()+"]");
            if (container.getContainerAlias().equals(helper.getBusinessActivityAliasMappings().get(processId))){
                instances = connector.getProcessClient().findProcessInstances(container.getContainerId(), 0, 100);
            }
        }

       LOGGER.debug("--------------- Existing Business Processes in Container ID ["+helper.getBusinessActivityAliasMappings().get(processId)+"] can filter by by Process ID ["+processId+"]  ---------------");
       for (ProcessInstance pInstance : instances){
            LOGGER.debug("\t\t "+pInstance.toString());
        }

        return ResponseEntity.ok((List<ProcessInstance>) instances);
    }  

    @RolesAllowed("kie-server")
    @PostMapping(value = "/tasks/{userId}")
	@ResponseBody
	public ResponseEntity<List<TaskSummary>> searchTasks(@RequestBody TaskSearch body, @PathVariable("userId") String userId) {
        LOGGER.debug("========================= Search (API: searchTasks called =================");
        LOGGER.debug("/t Search with filters ["+body.toString()+"]");

        //BusinessServiceMappingsHelper helper = new BusinessServiceMappingsHelper(true);

        Map<String, AbstractKieServerConnector> connectors = new HashMap<String, AbstractKieServerConnector>();

        if (body.getProcessId()== null){
            LOGGER.debug("/t WARNING: No filter indications based on Process TYpe need to search all services + all alias. This will take some time1!!");
            Map<String, String> knownRhpamServices = helper.getBusinessActivityAliasMappings();

            for (Map.Entry<String, String> entry : knownRhpamServices.entrySet()) {
                String alias = entry.getKey();
                String serviceUrl = entry.getValue();
                LOGGER.debug("/t Connector created for Alias ["+alias+"] and service URL ["+serviceUrl+"]");
                connectors.put(alias, new AbstractKieServerConnector(serviceUrl));
            }

        } else {
            LOGGER.debug("/t Single connector created based on Process ID ["+body.getProcessId()+"] to seach one service for tasks");
            String processId = helper.getBusinessActivityAliasMappings().get(body.getProcessId());
            connectors.put(processId, new AbstractKieServerConnector(body.getProcessId()));

        }

        // TODO 
        // maybe we provide a list of processes to search for my tasks in ... which will be mapped to KIE Servers to aggregate from???
        LOGGER.debug("Our job is now to get all Tasks (how since maybe many services ... need to agree on which service we want tasks -or list- to create aggregate)");
        LOGGER.debug("Our job is then to retrieve tasks from Service(s)(SQL for this?) ");


        // List<TaskSummary> findTasksOwned(String userId, Integer page, Integer pageSize);
        // List<TaskSummary> findTasksOwned(String userId, List<String> status, Integer page, Integer pageSize);

        List<TaskSummary> tasks = new ArrayList<TaskSummary>();
        for (Map.Entry<String, AbstractKieServerConnector> entry : connectors.entrySet()) {
            String alias = entry.getKey();
            AbstractKieServerConnector svcConnector = entry.getValue();
            
            // if filters for extra groups given call 
            if (body != null && body.getGroupFilters() != null && body.getGroupFilters().size() > 0) {
                tasks = svcConnector.getTaskClient().findTasksAssignedAsPotentialOwner(userId, body.getGroupFilters(), body.getTaskStatusfilters(), 0, 100);
            } else {
                if (body != null && body.getTaskStatusfilters() != null && body.getTaskStatusfilters().size() > 0) {
                // only task owned and with status in list
                    tasks = svcConnector.getTaskClient().findTasksOwned(userId, body.getTaskStatusfilters(), 0, 100);
                } else {
                    tasks = svcConnector.getTaskClient().findTasksOwned(userId, 0, 100);
                }
            }  
  
        }


        // //curl -X GET "http://localhost:8180/kie-server/services/rest/server/queries/tasks/instances/pot-owners?status=Ready&groups=financialapprover&page=0&pageSize=10&sortOrder=true" -H  "accept: application/json"
        // TaskInstance findTaskById(Long taskId);
        // TaskInstance findTaskById(Long taskId, boolean withSLA);  
        // List<TaskSummary> findTasksAssignedAsBusinessAdministrator(String userId, Integer page, Integer pageSize);
        //     List<TaskSummary> findTasksAssignedAsBusinessAdministrator(String userId, List<String> status, Integer page, Integer pageSize);
        //     List<TaskSummary> findTasksAssignedAsPotentialOwner(String userId, Integer page, Integer pageSize);
        // List<TaskSummary> findTasksAssignedAsPotentialOwner(String userId, List<String> status, Integer page, Integer pageSize);
        // List<TaskSummary> findTasksAssignedAsPotentialOwner(String userId, String filter, List<String> status, Integer page, Integer pageSize);
        // List<TaskSummary> findTasksAssignedAsPotentialOwner(String userId, List<String> groups, List<String> status, Integer page, Integer pageSize);
        // List<TaskSummary> findTasksOwned(String userId, Integer page, Integer pageSize);
        // List<TaskSummary> findTasksOwned(String userId, List<String> status, Integer page, Integer pageSize);
        // List<TaskSummary> findTasksByStatusByProcessInstanceId(Long processInstanceId, List<String> status, Integer page, Integer pageSize);
        // List<TaskSummary> findTasks(String userId, Integer page, Integer pageSize);
        // List<TaskEventInstance> findTaskEvents(Long taskId, Integer page, Integer pageSize);
    
        return ResponseEntity.ok((List<TaskSummary>) tasks);

    }  

    @RolesAllowed("kie-server")
    @RequestMapping(value = "/task/{id}/{action}", method = RequestMethod.POST)
	@ResponseBody
    public ResponseEntity<String> taskAction(@PathVariable("id") Long id,  @PathVariable("action") String action,
                            @RequestBody TaskCompleteActionMessage body) {
       
        LOGGER.debug("========================= Task Action (API: taskAction called =================");
        LOGGER.debug("\t Do action ["+action+"] on Task ["+id+"]");

        //AbstractKieServerConnector connector = createBusinessSvcConnector(((TaskActionMessage)body).getProcessId());
        AbstractKieServerConnector connector = createBusinessSvcConnector(body.getProcessId());
        //BusinessServiceMappingsHelper helper = new BusinessServiceMappingsHelper(true);

        // TODO 
        // claim, complete, forward, delegate, release,
        // we need the container/alias to act on task ... how to do generically? save ProcessInstanceId vs container alias in db?
        // maybe request gives as the ProcessInstanceId
        // We also need a generic structure to support each action command
        //Complete a Task with a comment and variables (the variables must be generic to support any Human Task)

        if (action != null && action.equals("COMPLETE")){
            LOGGER.debug("\t COMPLETE ACTION");

            TaskCompleteActionMessage taskActionInfo = (TaskCompleteActionMessage) body;
            LOGGER.debug("\t Action Information ["+(TaskCompleteActionMessage) body+"] ");

            //CLAIMS, STARTS, COMPLETES
            connector.getTaskClient().completeAutoProgress(taskActionInfo.getTaskContainerId(), 
                                    id, taskActionInfo.getActionUserId(), taskActionInfo.taskVarsMapper());

        } else if (action != null && action.equals("CLAIM")) {
            LOGGER.debug("\t CLAIM ACTION");

           // connector.getTaskClient().claimTask(containerId, taskId, userId);


        } else if (action != null && action.equals("START")) {
            LOGGER.debug("\t START ACTION");

            //connector.getTaskClient()void resumeTask(String containerId, Long taskId, String userId);

        } else if (action != null && action.equals("RELEASE")) {
            LOGGER.debug("\t RELEASE ACTION");
                        
            //connector.getTaskClient()releaseTask(String containerId, Long taskId, String userId);
        

        } else if (action != null && action.equals("DELEGATE")) {
            LOGGER.debug("\t DELEGATE ACTION");

           // connector.getTaskClient().delegateTask(String containerId, Long taskId, String userId, String targetUserId);

        } else if (action != null && action.equals("FORWARD")) {
            LOGGER.debug("\t FORWARD ACTION");

            //connector.getTaskClient()forwardTask(String containerId, Long taskId, String userId, String targetEntityId);

        } else {
            return ResponseEntity.ok("Action "+action+" not supported by API");
        }

        return ResponseEntity.ok("Action ["+action+"] Completed on Task ["+id+"]");
    }
    
    // TODO
  // ??? Add Human Task Comment (Used for history and rejection)  (TBD)



   // Search for a history on a specific business process based Task and Comment Tables and TaskVariableImpl for variable name pApprovalStatus (see Business Process documentation 2.1.5  Expenses Approvals   -  Tasks and Groups/Roles (TBD If custom query)
    
    @RolesAllowed("kie-server")
    @RequestMapping(value = "/expenses/history/{pid}", method = RequestMethod.GET)
	@ResponseBody
	public String taskApprovalsHistory(@PathVariable("pid") String pid) {
        LOGGER.debug("History of  pApprovalStatus, pApprovalComent and who made them (by task owner) IN PROCESS Instance ["+pid+"]");
        // TODO 
        // seacrch pApprovalStatus, pApprovalComent and who made them (by task owner) 
        LOGGER.debug("Our job is now to get all Tasks (how since maybe many services ... need to agree on which service we want tasks -or list- to create aggregate)");
        LOGGER.debug("Our job is then to retrieve tasks from Service(s)(SQL for this?) ");
        return "pApprovalStatus, pApprovalComent and who made them (by task owner) IN PROCESS Instance ["+pid+"] ";
    }

    @RequestMapping(value = "/anonymous", method = RequestMethod.GET)
    public ResponseEntity<String> getAnonymous() {
        return ResponseEntity.ok("Hello Anonymous");
    }

    @RolesAllowed("kie-server")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("Hello User");
    }

    @RolesAllowed("kie-server")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ResponseEntity<String> getAdmin() {

        return ResponseEntity.ok("Hello Admin");
    }

    //@RolesAllowed({ "groupa", "group-b" })
    @RolesAllowed({ "kie-server" })
    @RequestMapping(value = "/all-user", method = RequestMethod.GET)
    public ResponseEntity<String> getAllUser() {
        return ResponseEntity.ok("Hello All User");
    }

    @RolesAllowed("kie-server")
    @RequestMapping(value = "/wrong/{taskId}/{actor}", method = RequestMethod.PUT)
    @ResponseBody
    public String postFoos(
        @PathVariable("taskId") String taskId,
        @PathVariable("actor") String actor) {
        // Task task = userTaskService.getTask(taskId);
        // if(task != null) {
        //     LOGGER.info("Task {} status {}", task.getId(), task.getTaskData().getStatus());
        //     if(task.getTaskData().getStatus() == Status.Reserved) {
        //         userTaskService.start(task.getId(), actor);
        //         userTaskService.complete(task.getId(), actor, null);
        //     }
        //     else if(task.getTaskData().getStatus() == Status.Ready) {
        //         userTaskService.claim(task.getId(), actor);
        //         userTaskService.start(task.getId(), actor);
        //         userTaskService.complete(task.getId(), actor, null);
        //     }
          LOGGER.debug("autoCompleteTask["+taskId+"] for actior ["+actor+"]");
//}

       // return Response.ok().build();
      // return ResponseEntity.ok("autoCompleteTask["+taskId+"] for actior ["+actor+"]");
      return "autoCompleteTask["+taskId+"] for actior ["+actor+"]";

    }

    @RolesAllowed("kie-server")
    @RequestMapping(value = "/tasks/{taskId}/{actor}", method = RequestMethod.PUT)
	@ResponseBody
	public String autoCompleteTask(@PathVariable("taskId") String taskId,  @PathVariable("actor") String actor) {
        LOGGER.debug("autoCompleteTask["+taskId+"] for actior ["+actor+"]");
        return "Post ["+actor+"] some Foos["+taskId+"]";
	}

    @RolesAllowed("kie-server")
    @RequestMapping(value="/tasks", method = RequestMethod.GET, produces={"application/json","application/xml"})
    public ResponseEntity<String> autoCompleteTaskGET() {
        // Task task = userTaskService.getTask(taskId);
        // if(task != null) {
        //     LOGGER.info("Task {} status {}", task.getId(), task.getTaskData().getStatus());
        //     if(task.getTaskData().getStatus() == Status.Reserved) {
        //         userTaskService.start(task.getId(), actor);
        //         userTaskService.complete(task.getId(), actor, null);
        //     }
        //     else if(task.getTaskData().getStatus() == Status.Ready) {
        //         userTaskService.claim(task.getId(), actor);
        //         userTaskService.start(task.getId(), actor);
        //         userTaskService.complete(task.getId(), actor, null);
        //     }
          LOGGER.debug("get tasks]");
//}

       // return Response.ok().build();
       return ResponseEntity.ok("get tasks");

    }

}
