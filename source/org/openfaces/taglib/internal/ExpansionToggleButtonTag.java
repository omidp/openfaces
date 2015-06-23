/*
 * OpenFaces - JSF Component Library 2.0
 * Copyright (C) 2007-2010, TeamDev Ltd.
 * licensing@openfaces.org
 * Unless agreed in writing the contents of this file are subject to
 * the GNU Lesser General Public License Version 2.1 (the "LGPL" License).
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * Please visit http://openfaces.org/licensing/ for more details.
 */
package org.openfaces.taglib.internal;

import org.openfaces.component.ExpansionToggleButton;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * @author Dmitry Pikhulya
 */
public class ExpansionToggleButtonTag extends ToggleCaptionButtonTag {
    @Override
    public String getComponentType() {
        return ExpansionToggleButton.COMPONENT_TYPE;
    }

    @Override
    public String getRendererType() {
        return "org.openfaces.ExpansionToggleButtonRenderer";
    }

    @Override
    public void setComponentProperties(FacesContext facesContext, UIComponent component) {
        super.setComponentProperties(facesContext, component);

        setStringProperty(component, "expandedImageUrl");
        setStringProperty(component, "expandedRolloverImageUrl");
        setStringProperty(component, "expandedPressedImageUrl");
        setStringProperty(component, "collapsedImageUrl");
        setStringProperty(component, "collapsedRolloverImageUrl");
        setStringProperty(component, "collapsedPressedImageUrl");
    }

}
