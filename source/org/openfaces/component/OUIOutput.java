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
package org.openfaces.component;

import org.openfaces.util.ValueBindings;

import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;

/**
 * @author Dmitry Pikhulya
 */
public class OUIOutput extends UIOutput implements OUIComponent {
    private String style;
    private String styleClass;
    private String rolloverStyle;
    private String rolloverClass;

    private String onclick;
    private String ondblclick;
    private String onmousedown;
    private String onmouseover;
    private String onmousemove;
    private String onmouseout;
    private String onmouseup;
    private String onfocus;
    private String onblur;
    private String onkeydown;
    private String onkeyup;
    private String onkeypress;
    private String oncontextmenu;

    public String getStyle() {
        return ValueBindings.get(this, "style", style);
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getRolloverStyle() {
        return ValueBindings.get(this, "rolloverStyle", rolloverStyle);
    }

    public void setRolloverStyle(String rolloverStyle) {
        this.rolloverStyle = rolloverStyle;
    }

    public String getRolloverClass() {
        return ValueBindings.get(this, "rolloverClass", rolloverClass);
    }

    public void setRolloverClass(String rolloverClass) {
        this.rolloverClass = rolloverClass;
    }

    public String getStyleClass() {
        return ValueBindings.get(this, "styleClass", styleClass);
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getOnkeypress() {
        return ValueBindings.get(this, "onkeypress", onkeypress);
    }

    public void setOnkeypress(String onkeypress) {
        this.onkeypress = onkeypress;
    }

    public String getOncontextmenu() {
        return ValueBindings.get(this, "oncontextmenu", oncontextmenu);
    }

    public void setOncontextmenu(String oncontextmenu) {
        this.oncontextmenu = oncontextmenu;
    }

    public String getOnclick() {
        return ValueBindings.get(this, "onclick", onclick);
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public String getOndblclick() {
        return ValueBindings.get(this, "ondblclick", ondblclick);
    }

    public void setOndblclick(String ondblclick) {
        this.ondblclick = ondblclick;
    }

    public String getOnmousedown() {
        return ValueBindings.get(this, "onmousedown", onmousedown);
    }

    public void setOnmousedown(String onmousedown) {
        this.onmousedown = onmousedown;
    }

    public String getOnmouseover() {
        return ValueBindings.get(this, "onmouseover", onmouseover);
    }

    public void setOnmouseover(String onmouseover) {
        this.onmouseover = onmouseover;
    }

    public String getOnmousemove() {
        return ValueBindings.get(this, "onmousemove", onmousemove);
    }

    public void setOnmousemove(String onmousemove) {
        this.onmousemove = onmousemove;
    }

    public String getOnmouseout() {
        return ValueBindings.get(this, "onmouseout", onmouseout);
    }

    public void setOnmouseout(String onmouseout) {
        this.onmouseout = onmouseout;
    }

    public String getOnmouseup() {
        return ValueBindings.get(this, "onmouseup", onmouseup);
    }

    public void setOnmouseup(String onmouseup) {
        this.onmouseup = onmouseup;
    }

    public String getOnfocus() {
        return ValueBindings.get(this, "onfocus", onfocus);
    }

    public void setOnfocus(String onfocus) {
        this.onfocus = onfocus;
    }

    public String getOnblur() {
        return ValueBindings.get(this, "onblur", onblur);
    }

    public void setOnblur(String onblur) {
        this.onblur = onblur;
    }

    public String getOnkeydown() {
        return ValueBindings.get(this, "onkeydown", onkeydown);
    }

    public void setOnkeydown(String onkeydown) {
        this.onkeydown = onkeydown;
    }

    public String getOnkeyup() {
        return ValueBindings.get(this, "onkeyup", onkeyup);
    }

    public void setOnkeyup(String onkeyup) {
        this.onkeyup = onkeyup;
    }


    @Override
    public Object saveState(FacesContext context) {
        return new Object[]{super.saveState(context),
                style,
                styleClass,
                rolloverStyle,
                rolloverClass,

                onkeypress,
                onclick,
                ondblclick,
                onmousedown,
                onmouseover,
                onmousemove,
                onmouseout,
                onmouseup,
                onfocus,
                onblur,
                onkeydown,
                onkeyup,
                oncontextmenu
        };
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object[] values = (Object[]) state;
        int i = 0;
        super.restoreState(context, values[i++]);
        style = (String) values[i++];
        styleClass = (String) values[i++];
        rolloverStyle = (String) values[i++];
        rolloverClass = (String) values[i++];

        onkeypress = (String) values[i++];
        onclick = (String) values[i++];
        ondblclick = (String) values[i++];
        onmousedown = (String) values[i++];
        onmouseover = (String) values[i++];
        onmousemove = (String) values[i++];
        onmouseout = (String) values[i++];
        onmouseup = (String) values[i++];
        onfocus = (String) values[i++];
        onblur = (String) values[i++];
        onkeydown = (String) values[i++];
        onkeyup = (String) values[i++];
        oncontextmenu = (String) values[i++];
    }

}
