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
package org.openfaces.taglib.jsp.chart;

import org.openfaces.taglib.internal.chart.AbstractStyledComponentTag;
import org.openfaces.taglib.internal.chart.ChartAxisTag;

import javax.el.ValueExpression;

/**
 * @author Ekaterina Shliakhovetskaya
 */
public class ChartAxisJspTag extends AbstractStyledComponentJspTag {

    public ChartAxisJspTag(AbstractStyledComponentTag delegate) {
        super(delegate);
    }

    public ChartAxisJspTag() {
        super(new ChartAxisTag());
    }

    public void setTicksVisible(ValueExpression ticksVisible) {
        getDelegate().setPropertyValue("ticksVisible", ticksVisible);
    }

    public void setDomain(ValueExpression domain) {
        getDelegate().setPropertyValue("domain", domain);
    }

    public void setLabelVisible(ValueExpression labelVisible) {
        getDelegate().setPropertyValue("labelVisible", labelVisible);
    }

    public void setTicksLabelsVisible(ValueExpression ticksLabelsVisible) {
        getDelegate().setPropertyValue("ticksLabelsVisible", ticksLabelsVisible);
    }
}