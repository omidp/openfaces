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
package org.openfaces.component.timetable;

import org.openfaces.org.json.JSONException;
import org.openfaces.org.json.JSONObject;

import java.util.Date;
import java.util.Map;

/**
 * @author Dmitry Pikhulya
 */
public class ReservedTimeEvent extends AbstractTimetableEvent {
    public ReservedTimeEvent() {
    }

    public ReservedTimeEvent(String id, String resourceId, Date start, Date end) {
        super(id, resourceId, start, end);
    }

    @Override
    public JSONObject toJSONObject(Map params) throws JSONException {
        JSONObject obj = super.toJSONObject(params);
        obj.put("type", "reserved");
        return obj;
    }
}
