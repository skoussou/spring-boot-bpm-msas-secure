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

import com.redhat.notifications.model.NotificationsModel;

@Wid(widfile="NotificationsWorkItemDefinitions.wid", name="NotificationsWorkItemDefinitions",
        displayName="NotificationsWorkItemDefinitions",
        defaultHandler="mvel: new com.redhat.servicetask.NotificationsWorkItemWorkItemHandler()",
        documentation = "notifications-service-task/index.html",
        category = "Red Hat Service Tasks",
        icon = "NotificationsWorkItemDefinitions.png",
        parameters={
            @WidParameter(name="Notification", required = true)
        },
        results={
            @WidResult(name="SampleResult")
        },
        mavenDepends={
            @WidMavenDepends(group="org.jbpm.contrib", artifact="notifications-service-task", version="7.33.0.Final-redhat-00003")
        },
        serviceInfo = @WidService(category = "Red Hat Service Tasks", description = "${description}",
                keywords = "",
                action = @WidAction(title = "Sends Notifications"),
                authinfo = @WidAuth(required = true, params = {"Notification"},
                        paramsdescription = {"Contains Notification Details (address, subject, message, notification ID)"},
                        referencesite = "referenceSiteURL")
        )
)
public class NotificationsWorkItemWorkItemHandler extends AbstractLogOrThrowWorkItemHandler {

    public void executeWorkItem(WorkItem workItem,
                                WorkItemManager manager) {
        try {
            RequiredParameterValidator.validate(this.getClass(),
               workItem);

            // sample parameters
            NotificationsModel notification = (NotificationsModel) workItem.getParameter("Notification");


            // complete workitem impl...
            System.out.println("-------- NotificationsWorkItemWorkItemHandler (Sample Implementation until integration service is available) -----------");
            System.out.println(" Notification to be sent by Service Task ["+notification.toString()+"]");
            System.out.println("------------------------------------------------------------------------------------------------------------------------");

            // return results
            String sampleResult = "sample result";
            Map<String, Object> results = new HashMap<String, Object>();
            results.put("SampleResult", sampleResult);


            manager.completeWorkItem(workItem.getId(), results);
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


