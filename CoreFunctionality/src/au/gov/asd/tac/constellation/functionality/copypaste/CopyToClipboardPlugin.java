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
package au.gov.asd.tac.constellation.functionality.copypaste;

import au.gov.asd.tac.constellation.graph.GraphReadMethods;
import au.gov.asd.tac.constellation.pluginframework.Plugin;
import au.gov.asd.tac.constellation.pluginframework.PluginInteraction;
import au.gov.asd.tac.constellation.pluginframework.logging.ConstellationLoggerHelper;
import au.gov.asd.tac.constellation.pluginframework.parameters.PluginParameters;
import au.gov.asd.tac.constellation.pluginframework.templates.SimpleReadPlugin;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.ServiceProvider;

/**
 * copy action
 *
 * @author algol
 */
@ServiceProvider(service = Plugin.class)
@Messages("CopyToClipboardPlugin=Copy To Clipboard")
public final class CopyToClipboardPlugin extends SimpleReadPlugin {

    @Override
    public void read(final GraphReadMethods rg, final PluginInteraction interaction, final PluginParameters parameters) throws InterruptedException {
        final String text = GraphCopyUtilities.copyGraphTextToSystemClipboard(rg);
        ConstellationLoggerHelper.copyPropertyBuilder(this, text.length(), ConstellationLoggerHelper.SUCCESS);

        GraphCopyUtilities.copySelectedGraphElementsToClipboard(rg);
    }
}
