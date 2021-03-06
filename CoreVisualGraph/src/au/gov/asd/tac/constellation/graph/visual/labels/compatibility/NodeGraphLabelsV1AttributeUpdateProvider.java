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
package au.gov.asd.tac.constellation.graph.visual.labels.compatibility;

import au.gov.asd.tac.constellation.graph.attribute.AttributeDescription;
import au.gov.asd.tac.constellation.graph.versioning.AttributeUpdateProvider;
import au.gov.asd.tac.constellation.graph.versioning.UpdateProvider;
import au.gov.asd.tac.constellation.graph.visual.labels.VertexGraphLabelsAttributeDescription;
import au.gov.asd.tac.constellation.visual.labels.GraphLabel;
import au.gov.asd.tac.constellation.visual.labels.GraphLabels;
import au.gov.asd.tac.constellation.visual.labels.compatibility.GraphLabelV0;
import au.gov.asd.tac.constellation.visual.labels.compatibility.GraphLabelsV0;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author algol
 */
@ServiceProvider(service = UpdateProvider.class)
public class NodeGraphLabelsV1AttributeUpdateProvider extends AttributeUpdateProvider {

    private static final AttributeDescription FROM_ATTRIBUTE;
    private static final AttributeDescription TO_ATTRIBUTE;

    static {
        try {
            FROM_ATTRIBUTE = VertexGraphLabelsAttributeDescriptionV0.class.newInstance();
            TO_ATTRIBUTE = VertexGraphLabelsAttributeDescription.class.newInstance();
        } catch (final IllegalAccessException | InstantiationException ex) {
            throw new IllegalArgumentException(String.format("Version provider %s unable to access required attribute descriptions %s or %s", NodeGraphLabelsV1AttributeUpdateProvider.class.getName(), VertexGraphLabelsAttributeDescriptionV0.class.getName(), VertexGraphLabelsAttributeDescription.class.getName()));
        }
    }

    @Override
    public AttributeDescription getAttributeDescription() {
        return FROM_ATTRIBUTE;
    }

    @Override
    public AttributeDescription getUpdatedAttributeDescription() {
        return TO_ATTRIBUTE;
    }

    @Override
    public Object updateAttributeValue(final Object value) {
        if (value == null) {
            return null;
        }

        final GraphLabelsV0 v0 = (GraphLabelsV0) value;
        final List<GraphLabelV0> labelsV0 = v0.getLabels();
        final List<GraphLabel> labels = new ArrayList<>();
        for (final GraphLabelV0 labelV0 : labelsV0) {
            final GraphLabel label = new GraphLabel(labelV0.getAttributeName(), labelV0.getColor(), labelV0.getSize());
            labels.add(label);
        }

        return new GraphLabels(labels);
    }
}
