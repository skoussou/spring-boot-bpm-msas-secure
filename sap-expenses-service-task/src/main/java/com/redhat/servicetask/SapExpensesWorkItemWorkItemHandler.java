/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.servicetask;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import org.jbpm.process.workitem.core.AbstractLogOrThrowWorkItemHandler;
import org.jbpm.process.workitem.core.util.RequiredParameterValidator;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;
import org.jbpm.process.workitem.core.util.Wid;
import org.jbpm.process.workitem.core.util.WidParameter;
import org.jbpm.process.workitem.core.util.WidResult;
import org.jbpm.process.workitem.core.util.service.WidAction;
import org.jbpm.process.workitem.core.util.service.WidAuth;
import org.jbpm.process.workitem.core.util.service.WidService;
import org.jbpm.process.workitem.core.util.WidMavenDepends;

import com.redhat.exceptions.BusinessException;

@Wid(widfile="SapExpensesWorkItemDefinitions.wid", name="SapExpensesWorkItemDefinitions",
        displayName="SapExpensesWorkItemDefinitions",
        defaultHandler="mvel: new com.redhat.servicetask.SapExpensesWorkItemWorkItemHandler()",
        documentation = "sap-expenses-service-task/index.html",
        category = "Red Hat Service Tasks",
        icon = "SapExpensesWorkItemDefinitions.png",
        parameters={
            @WidParameter(name="ExpenseFormCorrelationKey", required = true),
            @WidParameter(name="ExpenseOwnerID", required = true),
            @WidParameter(name="CauseBusinessError"),
            @WidParameter(name="CauseTechnicalError")
        },
        results={
            @WidResult(name="SampleResult")
        },
        mavenDepends={
            @WidMavenDepends(group="org.jbpm.contrib", artifact="sap-expenses-service-task", version="7.33.0.Final-redhat-00003")
        },
        serviceInfo = @WidService(category = "Red Hat Service Tasks", description = "${description}",
                keywords = "",
                action = @WidAction(title = "Sends SAP Updates"),
                authinfo = @WidAuth(required = true, params = {"ExpenseFormCorrelationKey", "ExpenseOwnerID"},
                        paramsdescription = {"Expense Form ID", "ID Of claimant"},
                        referencesite = "referenceSiteURL")
        )
)
public class SapExpensesWorkItemWorkItemHandler extends AbstractLogOrThrowWorkItemHandler {

    public void executeWorkItem(WorkItem workItem,
                                WorkItemManager manager) {
        try {
            RequiredParameterValidator.validate(this.getClass(),
               workItem);

            // sample parameters
            String expenseFormCorrelationKey = (String) workItem.getParameter("ExpenseFormCorrelationKey");
            String expenseOwnerID = (String) workItem.getParameter("ExpenseOwnerID");
            Boolean causeBusinessError = (Boolean) workItem.getParameter("CauseBusinessError");
            Boolean causeTechnicalError = (Boolean) workItem.getParameter("CauseTechnicalError");

            if (causeBusinessError != null && causeBusinessError == true){
              System.out.println("causeTechnicalError=true throwing com.redhat.exceptions.BusinessException exception");

              // Assumptions about the business errors from the SAP Service (Department Details are incorrect, Expene Type TAXI mismatch should be anadolu-Taxi)
              ArrayList<String> errorsReceived = new ArrayList<String>(Arrays.asList(new String[] {"Department Name does not exist", "Expene Type TAXI mismatch should be anadolu-Taxi"}));
              throw new BusinessException(null, "SAP Error 1499", expenseFormCorrelationKey, errorsReceived);

            } else if (causeTechnicalError  != null && causeTechnicalError == true) {
              System.out.println("causeTechnicalError=true throwing runtime exception");

              throw new RuntimeException("This should not really ever happen but we should handle in RHPAM");
            } else {
              // TODO - complete workitem impl...
              System.out.println("-------- SapExpensesWorkItemWorkItemHandler (Sample Implementation until integration service is available) -----------");
              System.out.println(" SAP will update budget records for Expense Form ["+expenseFormCorrelationKey+"] and payment to be sent to ["+expenseOwnerID+"]");
              System.out.println("------------------------------------------------------------------------------------------------------------------------");

              // return results
              String sampleResult = "sample result";
              Map<String, Object> results = new HashMap<String, Object>();
              results.put("SampleResult", sampleResult);
    
              manager.completeWorkItem(workItem.getId(), results);

          }
        } catch (BusinessException bizError) {
           throw bizError;
        } catch(Throwable cause) {
            handleException(cause);
        }
    }

    @Override
    public void abortWorkItem(WorkItem workItem,
                              WorkItemManager manager) {
        // stub
    }
}


