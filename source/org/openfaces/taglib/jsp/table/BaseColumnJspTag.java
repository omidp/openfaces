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
package org.openfaces.taglib.jsp.table;

import org.openfaces.taglib.internal.table.BaseColumnTag;
import org.openfaces.taglib.jsp.AbstractComponentJspTag;

import javax.el.ValueExpression;

/**
 * @author Dmitry Pikhulya
 */
public abstract class BaseColumnJspTag extends AbstractComponentJspTag {

    protected BaseColumnJspTag(BaseColumnTag delegate) {
        super(delegate);
    }

    public void setHeader(ValueExpression header) {
        getDelegate().setPropertyValue("header", header);
    }

    public void setFooter(ValueExpression footer) {
        getDelegate().setPropertyValue("footer", footer);
    }

    public void setWidth(ValueExpression width) {
        getDelegate().setPropertyValue("width", width);
    }

    public void setAlign(ValueExpression align) {
        getDelegate().setPropertyValue("align", align);
    }

    public void setValign(ValueExpression valign) {
        getDelegate().setPropertyValue("valign", valign);
    }

    public void setResizable(ValueExpression resizable) {
        getDelegate().setPropertyValue("resizable", resizable);
    }

    public void setMinResizingWidth(ValueExpression minResizingWidth) {
        getDelegate().setPropertyValue("minResizingWidth", minResizingWidth);
    }

    public void setFixed(ValueExpression fixed) {
        getDelegate().setPropertyValue("fixed", fixed);
    }

    public void setHeaderStyle(ValueExpression headerStyle) {
        getDelegate().setPropertyValue("headerStyle", headerStyle);
    }

    public void setHeaderClass(ValueExpression headerClass) {
        getDelegate().setPropertyValue("headerClass", headerClass);
    }

    public void setSubHeaderStyle(ValueExpression subHeaderStyle) {
        getDelegate().setPropertyValue("subHeaderStyle", subHeaderStyle);
    }

    public void setSubHeaderClass(ValueExpression subHeaderClass) {
        getDelegate().setPropertyValue("subHeaderClass", subHeaderClass);
    }

    public void setBodyStyle(ValueExpression bodyStyle) {
        getDelegate().setPropertyValue("bodyStyle", bodyStyle);
    }

    public void setBodyClass(ValueExpression bodyClass) {
        getDelegate().setPropertyValue("bodyClass", bodyClass);
    }

    public void setFooterStyle(ValueExpression footerStyle) {
        getDelegate().setPropertyValue("footerStyle", footerStyle);
    }

    public void setFooterClass(ValueExpression footerClass) {
        getDelegate().setPropertyValue("footerClass", footerClass);
    }

    public void setHeaderOnclick(ValueExpression headerOnclick) {
        getDelegate().setPropertyValue("headerOnclick", headerOnclick);
    }

    public void setHeaderOndblclick(ValueExpression headerOndblclick) {
        getDelegate().setPropertyValue("headerOndblclick", headerOndblclick);
    }

    public void setHeaderOnmousedown(ValueExpression headerOnmousedown) {
        getDelegate().setPropertyValue("headerOnmousedown", headerOnmousedown);
    }

    public void setHeaderOnmouseover(ValueExpression headerOnmouseover) {
        getDelegate().setPropertyValue("headerOnmouseover", headerOnmouseover);
    }

    public void setHeaderOnmousemove(ValueExpression headerOnmousemove) {
        getDelegate().setPropertyValue("headerOnmousemove", headerOnmousemove);
    }

    public void setHeaderOnmouseout(ValueExpression headerOnmouseout) {
        getDelegate().setPropertyValue("headerOnmouseout", headerOnmouseout);
    }

    public void setHeaderOnmouseup(ValueExpression headerOnmouseup) {
        getDelegate().setPropertyValue("headerOnmouseup", headerOnmouseup);
    }

    public void setBodyOnclick(ValueExpression bodyOnclick) {
        getDelegate().setPropertyValue("bodyOnclick", bodyOnclick);
    }

    public void setBodyOndblclick(ValueExpression bodyOndblclick) {
        getDelegate().setPropertyValue("bodyOndblclick", bodyOndblclick);
    }

    public void setBodyOnmousedown(ValueExpression bodyOnmousedown) {
        getDelegate().setPropertyValue("bodyOnmousedown", bodyOnmousedown);
    }

    public void setBodyOnmouseover(ValueExpression bodyOnmouseover) {
        getDelegate().setPropertyValue("bodyOnmouseover", bodyOnmouseover);
    }

    public void setBodyOnmousemove(ValueExpression bodyOnmousemove) {
        getDelegate().setPropertyValue("bodyOnmousemove", bodyOnmousemove);
    }

    public void setBodyOnmouseout(ValueExpression bodyOnmouseout) {
        getDelegate().setPropertyValue("bodyOnmouseout", bodyOnmouseout);
    }

    public void setBodyOnmouseup(ValueExpression bodyOnmouseup) {
        getDelegate().setPropertyValue("bodyOnmouseup", bodyOnmouseup);
    }

    public void setFooterOnclick(ValueExpression footerOnclick) {
        getDelegate().setPropertyValue("footerOnclick", footerOnclick);
    }

    public void setFooterOndblclick(ValueExpression footerOndblclick) {
        getDelegate().setPropertyValue("footerOndblclick", footerOndblclick);
    }

    public void setFooterOnmousedown(ValueExpression footerOnmousedown) {
        getDelegate().setPropertyValue("footerOnmousedown", footerOnmousedown);
    }

    public void setFooterOnmouseover(ValueExpression footerOnmouseover) {
        getDelegate().setPropertyValue("footerOnmouseover", footerOnmouseover);
    }

    public void setFooterOnmousemove(ValueExpression footerOnmousemove) {
        getDelegate().setPropertyValue("footerOnmousemove", footerOnmousemove);
    }

    public void setFooterOnmouseout(ValueExpression footerOnmouseout) {
        getDelegate().setPropertyValue("footerOnmouseout", footerOnmouseout);
    }

    public void setFooterOnmouseup(ValueExpression footerOnmouseup) {
        getDelegate().setPropertyValue("footerOnmouseup", footerOnmouseup);
    }
    
    public void setBaseColumn(ValueExpression baseColumn) {
        getDelegate().setPropertyValue("baseColumn", baseColumn);
    }

}
