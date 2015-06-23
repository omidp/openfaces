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
package org.openfaces.taglib.internal.filter;

import org.openfaces.component.filter.InputTextFilter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * @author Dmitry Pikhulya
 */
public class InputTextFilterTag extends ExpressionFilterTag {
    public String getComponentType() {
        return InputTextFilter.COMPONENT_TYPE;
    }

    public String getRendererType() {
        return "org.openfaces.InputTextFilterRenderer";
    }

    @Override
    public void setComponentProperties(FacesContext facesContext, UIComponent component) {
        super.setComponentProperties(facesContext, component);

        setStringProperty(component, "focusedStyle");
        setStringProperty(component, "focusedClass");
        setStringProperty(component, "dir");
        setStringProperty(component, "lang");
        setStringProperty(component, "alt");
        setIntProperty(component, "maxlength");
        setIntProperty(component, "size");
        setStringProperty(component, "autocomplete");
    }

}
