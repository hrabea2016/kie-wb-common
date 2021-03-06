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

package org.kie.workbench.common.stunner.cm.client.shape.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.ait.lienzo.client.core.shape.wires.ILayoutHandler;
import org.kie.workbench.common.stunner.bpmn.definition.UserTask;
import org.kie.workbench.common.stunner.cm.client.shape.CaseManagementShape;
import org.kie.workbench.common.stunner.cm.client.shape.view.CaseManagementShapeView;
import org.kie.workbench.common.stunner.cm.client.wires.HorizontalStackLayoutManager;
import org.kie.workbench.common.stunner.cm.client.wires.VerticalStackLayoutManager;
import org.kie.workbench.common.stunner.cm.definition.AdHocSubprocess;
import org.kie.workbench.common.stunner.cm.definition.CaseManagementDiagram;
import org.kie.workbench.common.stunner.cm.definition.CaseReusableSubprocess;
import org.kie.workbench.common.stunner.cm.definition.ProcessReusableSubprocess;

public class CaseManagementShapeCommand {

    interface Command {
        CaseManagementShape configure(CaseManagementShapeView shapeView);
    }

    private static final Map<Class, Command> CM_SHAPE_TYPES;
    private static final String DIAGRAM = "";
    private static final String STAGE = "stage";
    private static final String SUBCASE = "subcase";
    private static final String SUBPROCESS = "subprocess";
    private static final String USER_TASK = "task";

    static {
        final Map<Class, Command> cmShapeTypes = new HashMap<>();

        cmShapeTypes.put(CaseManagementDiagram.class, (CaseManagementShapeView shapeView) -> {
            shapeView.setLabel(DIAGRAM);
            shapeView.setLayoutHandler(new HorizontalStackLayoutManager());
            return new CaseManagementShape(shapeView);
        });
        cmShapeTypes.put(AdHocSubprocess.class, (CaseManagementShapeView shapeView) -> {
            shapeView.setLabel(STAGE);
            shapeView.setLayoutHandler(new VerticalStackLayoutManager());
            return new CaseManagementShape(shapeView);
        });
        cmShapeTypes.put(ProcessReusableSubprocess.class, (CaseManagementShapeView shapeView) -> {
            shapeView.setLabel(SUBPROCESS);
            shapeView.setLayoutHandler(ILayoutHandler.NONE);
            return new CaseManagementShape(shapeView);
        });
        cmShapeTypes.put(CaseReusableSubprocess.class, (CaseManagementShapeView shapeView) -> {
            shapeView.setLabel(SUBCASE);
            shapeView.setLayoutHandler(ILayoutHandler.NONE);
            return new CaseManagementShape(shapeView);
        });
        cmShapeTypes.put(UserTask.class, (CaseManagementShapeView shapeView) -> {
            shapeView.setLabel(USER_TASK);
            shapeView.setLayoutHandler(ILayoutHandler.NONE);
            return new CaseManagementShape(shapeView);
        });
        CM_SHAPE_TYPES = Collections.unmodifiableMap(cmShapeTypes);
    }

    public static CaseManagementShape create(Class clazz, CaseManagementShapeView shapeView) {
        Command command = CM_SHAPE_TYPES.get(clazz);
        if (command == null) {
            throw new IllegalArgumentException("Unsupported shapeView type: " + clazz.getSimpleName());
        }
        return command.configure(shapeView);
    }
}
