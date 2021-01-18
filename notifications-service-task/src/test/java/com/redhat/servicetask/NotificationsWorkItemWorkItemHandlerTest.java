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

import org.drools.core.process.instance.impl.WorkItemImpl;
import org.jbpm.process.workitem.core.TestWorkItemManager;
import org.jbpm.test.AbstractBaseTest;
import org.junit.Test;
import static org.junit.Assert.*;

import com.redhat.notifications.model.NotificationsModel;

public class NotificationsWorkItemWorkItemHandlerTest extends AbstractBaseTest {

    @Test
    public void testHandler() throws Exception {
        WorkItemImpl workItem = new WorkItemImpl();


com.redhat.notifications.model.NotificationsModel notification = new com.redhat.notifications.model.NotificationsModel("test@email.com", "Message Subject: Test", "The full message will be here", "Custom Unique ID");
        workItem.setParameter("Notification", notification);


        TestWorkItemManager manager = new TestWorkItemManager();

        NotificationsWorkItemWorkItemHandler handler = new NotificationsWorkItemWorkItemHandler();
        handler.setLogThrownException(true);
        handler.executeWorkItem(workItem,
                                manager);

        assertNotNull(manager.getResults());
        assertEquals(1,
                     manager.getResults().size());
        assertTrue(manager.getResults().containsKey(workItem.getId()));
    }
}
