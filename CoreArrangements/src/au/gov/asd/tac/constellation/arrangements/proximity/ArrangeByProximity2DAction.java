/*
 * Copyright 2010-2019 Australian Signals Directorate
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
package au.gov.asd.tac.constellation.arrangements.proximity;

import au.gov.asd.tac.constellation.arrangements.ArrangementPluginRegistry;
import au.gov.asd.tac.constellation.functionality.CorePluginRegistry;
import au.gov.asd.tac.constellation.graph.node.GraphNode;
import au.gov.asd.tac.constellation.pluginframework.PluginExecutor;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

/**
 *
 * @author algol
 */
@ActionID(category = "Arrange", id = "au.gov.asd.tac.constellation.arrangements.misc.ArrangeByProximity2DAction")
@ActionRegistration(displayName = "#CTL_ArrangeByProximity2DAction", surviveFocusChange = true)
@ActionReferences({
    @ActionReference(path = "Menu/Arrange", position = 400),
    @ActionReference(path = "Shortcuts", name = "C-P")
})
@Messages("CTL_ArrangeByProximity2DAction=Proximity")
public final class ArrangeByProximity2DAction extends AbstractAction {

    private final GraphNode context;

    public ArrangeByProximity2DAction(final GraphNode context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        PluginExecutor.startWith(ArrangementPluginRegistry.FR2D)
                .followedBy(CorePluginRegistry.RESET)
                .executeWriteLater(context.getGraph(), Bundle.CTL_ArrangeByProximity2DAction());
    }
}
