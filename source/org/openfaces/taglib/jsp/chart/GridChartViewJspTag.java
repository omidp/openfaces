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

import org.openfaces.taglib.internal.chart.AbstractChartViewTag;

import javax.el.ValueExpression;

/**
 * @author Ekaterina Shliakhovetskaya
 */
public abstract class GridChartViewJspTag extends AbstractChartViewJspTag {

    GridChartViewJspTag(AbstractChartViewTag delegate) {
        super(delegate);
    }

    public void setKeyAxisLabel(ValueExpression keyAxisLabel) {
        getDelegate().setPropertyValue("keyAxisLabel", keyAxisLabel);
    }

    public void setValueAxisLabel(ValueExpression valueAxisLabel) {
        getDelegate().setPropertyValue("valueAxisLabel", valueAxisLabel);
    }

    public void setShowAxes(ValueExpression showAxes) {
        getDelegate().setPropertyValue("showAxes", showAxes);
    }

    public void setLabelsVisible(ValueExpression labelsVisible) {
        getDelegate().setPropertyValue("labelsVisible", labelsVisible);
    }

    public void setOrientation(ValueExpression orientation) {
        getDelegate().setPropertyValue("orientation", orientation);
    }


}