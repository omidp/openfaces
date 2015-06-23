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
package org.openfaces.taglib.internal.command;

import org.openfaces.component.command.CommandButton;
import org.openfaces.taglib.internal.OUICommandTag;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * @author Dmitry Pikhulya
 */
public class CommandButtonTag extends OUICommandTag {
    public String getComponentType() {
        return CommandButton.COMPONENT_TYPE;
    }

    public String getRendererType() {
        return "org.openfaces.CommandButtonRenderer";
    }

    @Override
    public void setComponentProperties(FacesContext context, UIComponent component) {
        super.setComponentProperties(context, component);

        setStringProperty(component, "value");
        setBooleanProperty(component, "disabled");
        setStringProperty(component, "type");
        setStringProperty(component, "accesskey");
        setStringProperty(component, "tabindex");
        setStringProperty(component, "lang");
        setStringProperty(component, "title");
        setStringProperty(component, "alt");
        setStringProperty(component, "dir");
        setStringProperty(component, "image");
    }
}