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
package org.openfaces.taglib.facelets.chart;

import com.sun.facelets.FaceletContext;
import com.sun.facelets.tag.Metadata;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.TagAttributeException;
import org.openfaces.component.chart.ChartDomain;
import org.openfaces.util.Enumerations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Ekaterina Shliakhovetskaya
 */
public class ChartDomainPropertyMetadata extends Metadata {// todo: use common internal tag handlers and remove these classes
    private final Method method;
    private final TagAttribute attribute;

    public void applyMetadata(FaceletContext ctx, Object instance) {
        ChartDomain d = Enumerations.valueByString(
                ChartDomain.class, attribute.getValue(), ChartDomain.BOTH, "domain");
        try {
            method.invoke(instance, d);
        }
        catch (InvocationTargetException e) {
            throw new TagAttributeException(attribute, e.getCause());
        }
        catch (Exception e) {
            throw new TagAttributeException(attribute, e);
        }
    }

    public ChartDomainPropertyMetadata(Method method, TagAttribute attribute) {
        this.method = method;
        this.attribute = attribute;
    }
}

