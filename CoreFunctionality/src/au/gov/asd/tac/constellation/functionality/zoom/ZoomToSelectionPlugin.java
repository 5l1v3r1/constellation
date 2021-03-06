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
package au.gov.asd.tac.constellation.functionality.zoom;

import au.gov.asd.tac.constellation.graph.GraphWriteMethods;
import au.gov.asd.tac.constellation.graph.interaction.animation.Animation;
import au.gov.asd.tac.constellation.graph.interaction.animation.PanAnimation;
import au.gov.asd.tac.constellation.graph.visual.camera.BoundingBoxUtilities;
import au.gov.asd.tac.constellation.graph.visual.camera.CameraUtilities;
import au.gov.asd.tac.constellation.graph.visual.utilities.VisualGraphUtilities;
import au.gov.asd.tac.constellation.pluginframework.Plugin;
import au.gov.asd.tac.constellation.pluginframework.PluginException;
import au.gov.asd.tac.constellation.pluginframework.PluginInfo;
import au.gov.asd.tac.constellation.pluginframework.PluginInteraction;
import au.gov.asd.tac.constellation.pluginframework.PluginType;
import au.gov.asd.tac.constellation.pluginframework.parameters.PluginParameters;
import au.gov.asd.tac.constellation.pluginframework.templates.SimpleEditPlugin;
import au.gov.asd.tac.constellation.visual.camera.BoundingBox;
import au.gov.asd.tac.constellation.visual.camera.Camera;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.ServiceProvider;

/**
 * Reset the camera.
 *
 * @author algol
 */
@ServiceProvider(service = Plugin.class)
@PluginInfo(minLogInterval = 5000, pluginType = PluginType.DISPLAY, tags = {"LOW LEVEL"})
@Messages("ZoomToSelectionPlugin=Zoom to Selection")
public final class ZoomToSelectionPlugin extends SimpleEditPlugin {

    @Override
    public void edit(final GraphWriteMethods graph, final PluginInteraction interaction, final PluginParameters parameters) throws InterruptedException, PluginException {
        final Camera oldCamera = VisualGraphUtilities.getCamera(graph);
        final BoundingBox box = new BoundingBox();
        final Camera camera = new Camera(oldCamera);
        BoundingBoxUtilities.recalculateFromGraph(box, graph, true);
        CameraUtilities.zoomToBoundingBox(camera, box);
        Animation.startAnimation(new PanAnimation("Zoom to Selection", oldCamera, camera, true));
    }
}
