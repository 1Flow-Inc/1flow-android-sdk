/*
 *  Copyright 2021 1Flow, Inc.
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.oneflow.analytics.model.events;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OFEventSubmitResponse {

    @SerializedName("update")
    private OFEventUpdate eventUpdate;
    @SerializedName("data")
    private ArrayList<OFRecordEventsTabToAPI> data;

    public OFEventUpdate getEventUpdate() {
        return eventUpdate;
    }

    public void setEventUpdate(OFEventUpdate OFEventUpdate) {
        this.eventUpdate = OFEventUpdate;
    }

    public ArrayList<OFRecordEventsTabToAPI> getData() {
        return data;
    }

    public void setData(ArrayList<OFRecordEventsTabToAPI> data) {
        this.data = data;
    }
}
