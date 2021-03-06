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
package org.kie.workbench.common.stunner.project.client.session;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.stunner.core.client.session.ClientSession;
import org.kie.workbench.common.stunner.core.client.session.command.ManagedClientSessionCommands;
import org.kie.workbench.common.stunner.core.client.session.command.impl.ClearSessionCommand;
import org.kie.workbench.common.stunner.core.client.session.command.impl.CopySelectionSessionCommand;
import org.kie.workbench.common.stunner.core.client.session.command.impl.CutSelectionSessionCommand;
import org.kie.workbench.common.stunner.core.client.session.command.impl.DeleteSelectionSessionCommand;
import org.kie.workbench.common.stunner.core.client.session.command.impl.ExportToBpmnSessionCommand;
import org.kie.workbench.common.stunner.core.client.session.command.impl.ExportToJpgSessionCommand;
import org.kie.workbench.common.stunner.core.client.session.command.impl.ExportToPdfSessionCommand;
import org.kie.workbench.common.stunner.core.client.session.command.impl.ExportToPngSessionCommand;
import org.kie.workbench.common.stunner.core.client.session.command.impl.ExportToSvgSessionCommand;
import org.kie.workbench.common.stunner.core.client.session.command.impl.PasteSelectionSessionCommand;
import org.kie.workbench.common.stunner.core.client.session.command.impl.RedoSessionCommand;
import org.kie.workbench.common.stunner.core.client.session.command.impl.SaveDiagramSessionCommand;
import org.kie.workbench.common.stunner.core.client.session.command.impl.SwitchGridSessionCommand;
import org.kie.workbench.common.stunner.core.client.session.command.impl.UndoSessionCommand;
import org.kie.workbench.common.stunner.core.client.session.command.impl.ValidateSessionCommand;
import org.kie.workbench.common.stunner.core.client.session.command.impl.VisitGraphSessionCommand;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EditorSessionCommandsTest {

    @Mock
    protected ManagedClientSessionCommands commands;

    @Mock
    protected ClientSession session;

    protected EditorSessionCommands editorSessionCommands;

    @Before
    @SuppressWarnings("unchecked")
    public void setup() {
        this.editorSessionCommands = makeEditorSessionCommands();

        when(commands.register(any(Class.class))).thenReturn(commands);
    }

    protected EditorSessionCommands makeEditorSessionCommands() {
        return new EditorSessionCommands(commands);
    }

    @Test
    public void testInit() {
        editorSessionCommands.init();

        final InOrder inOrder = inOrder(commands);

        inOrder.verify(commands).register(VisitGraphSessionCommand.class);
        inOrder.verify(commands).register(SwitchGridSessionCommand.class);
        inOrder.verify(commands).register(ClearSessionCommand.class);
        inOrder.verify(commands).register(DeleteSelectionSessionCommand.class);
        inOrder.verify(commands).register(UndoSessionCommand.class);
        inOrder.verify(commands).register(RedoSessionCommand.class);
        inOrder.verify(commands).register(ValidateSessionCommand.class);
        inOrder.verify(commands).register(ExportToPngSessionCommand.class);
        inOrder.verify(commands).register(ExportToJpgSessionCommand.class);
        inOrder.verify(commands).register(ExportToPdfSessionCommand.class);
        inOrder.verify(commands).register(ExportToSvgSessionCommand.class);
        inOrder.verify(commands).register(ExportToBpmnSessionCommand.class);
        inOrder.verify(commands).register(CopySelectionSessionCommand.class);
        inOrder.verify(commands).register(PasteSelectionSessionCommand.class);
        inOrder.verify(commands).register(CutSelectionSessionCommand.class);
        inOrder.verify(commands).register(SaveDiagramSessionCommand.class);
    }

    @Test
    public void testBind() {
        editorSessionCommands.bind(session);

        verify(commands).bind(session);
    }

    @Test
    public void testGetCommands() {
        assertEquals(commands, editorSessionCommands.getCommands());
    }

    @Test
    public void testGetVisitGraphSessionCommand() {
        editorSessionCommands.getVisitGraphSessionCommand();

        verify(commands).get(0);
    }

    @Test
    public void testGetSwitchGridSessionCommand() {
        editorSessionCommands.getSwitchGridSessionCommand();

        verify(commands).get(1);
    }

    @Test
    public void testGetClearSessionCommand() {
        editorSessionCommands.getClearSessionCommand();

        verify(commands).get(2);
    }

    @Test
    public void testGetDeleteSelectionSessionCommand() {
        editorSessionCommands.getDeleteSelectionSessionCommand();

        verify(commands).get(3);
    }

    @Test
    public void testGetUndoSessionCommand() {
        editorSessionCommands.getUndoSessionCommand();

        verify(commands).get(4);
    }

    @Test
    public void testGetRedoSessionCommand() {
        editorSessionCommands.getRedoSessionCommand();

        verify(commands).get(5);
    }

    @Test
    public void testGetValidateSessionCommand() {
        editorSessionCommands.getValidateSessionCommand();

        verify(commands).get(6);
    }

    @Test
    public void testGetExportToPngSessionCommand() {
        editorSessionCommands.getExportToPngSessionCommand();

        verify(commands).get(7);
    }

    @Test
    public void testGetExportToJpgSessionCommand() {
        editorSessionCommands.getExportToJpgSessionCommand();

        verify(commands).get(8);
    }

    @Test
    public void testGetExportToPdfSessionCommand() {
        editorSessionCommands.getExportToPdfSessionCommand();

        verify(commands).get(9);
    }

    @Test
    public void testGetExportToSvgSessionCommand() {
        editorSessionCommands.getExportToSvgSessionCommand();

        verify(commands).get(10);
    }

    @Test
    public void testGetExportToBpmnSessionCommand() {
        editorSessionCommands.getExportToBpmnSessionCommand();

        verify(commands).get(11);
    }

    @Test
    public void testGetCopySelectionSessionCommand() {
        editorSessionCommands.getCopySelectionSessionCommand();

        verify(commands).get(12);
    }

    @Test
    public void testGetPasteSelectionSessionCommand() {
        editorSessionCommands.getPasteSelectionSessionCommand();

        verify(commands).get(13);
    }

    @Test
    public void testGetCutSelectionSessionCommand() {
        editorSessionCommands.getCutSelectionSessionCommand();

        verify(commands).get(14);
    }

    @Test
    public void testGetSaveDiagramSessionCommand() {
        editorSessionCommands.getSaveDiagramSessionCommand();

        verify(commands).get(15);
    }

    @Test
    public void testGet() {
        IntStream.range(0, 15).forEach(i -> {
            editorSessionCommands.get(i);
            verify(commands).get(i);
        });
    }
}
