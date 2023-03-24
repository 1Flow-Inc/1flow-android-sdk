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

package com.oneflow.analytics.utils;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;

import com.oneflow.analytics.model.events.OFEventAPIRequest;
import com.oneflow.analytics.model.events.OFRecordEventsTab;
import com.oneflow.analytics.model.events.OFRecordEventsTabKT;
import com.oneflow.analytics.model.events.OFRecordEventsTabToAPI;
import com.oneflow.analytics.repositories.OFEventAPIRepo;
import com.oneflow.analytics.repositories.OFEventDBRepoKT;
import com.oneflow.analytics.sdkdb.OFOneFlowSHP;

import java.util.ArrayList;

public class OFMyCountDownTimer extends CountDownTimer implements OFMyResponseHandlerOneFlow {
    Context mContext;
    static OFMyCountDownTimer cdt;

    public static synchronized OFMyCountDownTimer getInstance(Context context, Long duration, Long interval) {
        if (cdt == null) {
            cdt = new OFMyCountDownTimer(context, duration, interval);

        }
        return cdt;
    }

    private OFMyCountDownTimer(Context context, Long duration, Long interval) {
        super(duration, interval);
        this.mContext = context;
        OFHelper.v("MyCountDownTimer", "1Flow Constructor called");
    }

    @Override
    public void onTick(long millisUntilFinished) {
        OFHelper.v("MyCountDownTimer", "1Flow tick called");
        if (OFHelper.isConnected(mContext)) {
            //OFEventDBRepo.fetchEvents(mContext, this, OFConstants.ApiHitType.fetchEventsFromDB);
            new OFEventDBRepoKT().fetchEvents(mContext,this,OFConstants.ApiHitType.fetchEventsFromDB);

        }
    }

    @Override
    public void onFinish() {
        OFHelper.v("MyCountDownTimer", "1Flow finish called");
    }

    @Override
    public void onResponseReceived(OFConstants.ApiHitType hitType, Object obj, Long reserve, String reserved,Object obj2, Object obj3) {
        OFHelper.v("OFMyCountDownTimer", "1Flow onReceived type[" + hitType + "]");
        switch (hitType) {

            case fetchEventsFromDB:

                OFHelper.v("OFMyCountDownTimer", "1Flow fetchEventsFromDB came back");

                if(obj!=null) {
                    ArrayList<OFRecordEventsTab> list = (ArrayList<OFRecordEventsTab>) obj;
                    OFHelper.v("OFMyCountDownTimer", "1Flow fetchEventsFromDB list received size[" + list.size() + "]");
                    //Preparing list to send api
                    if (list.size() > 0) {
                        Integer[] ids = new Integer[list.size()];
                        int i = 0;
                        ArrayList<OFRecordEventsTabToAPI> retListToAPI = new ArrayList<>();
                        OFRecordEventsTabToAPI retMain;
                        for (OFRecordEventsTab ret : list) {
                            retMain = new OFRecordEventsTabToAPI();
                            retMain.setPlatform("a");
                            retMain.setEventName(ret.getEventName());
                            retMain.setTime(ret.getTime());
                            retMain.setDataMap(ret.getDataMap());
                            retListToAPI.add(retMain);

                            ids[i++] = ret.getId();
                        }
                        OFOneFlowSHP shp = OFOneFlowSHP.getInstance(mContext);
                        if (shp.getUserDetails() != null) {
                            if (!OFHelper.validateString(shp.getUserDetails().getAnalytic_user_id()).equalsIgnoreCase("NA")) {
                                OFEventAPIRequest ear = new OFEventAPIRequest();
                                ear.setUserId(shp.getUserDetails().getAnalytic_user_id());
                                ear.setEvents(retListToAPI);
                                OFHelper.v("OFMyCountDownTimer", "1Flow fetchEventsFromDB request prepared");
                                shp.storeValue(OFConstants.SHP_EVENTS_DELETE_PENDING,true);
                                OFEventAPIRepo.sendLogsToApi(shp.getStringValue(OFConstants.APPIDSHP), ear, this, OFConstants.ApiHitType.sendEventsToAPI, ids);
                            }
                        }
                    }
                }
                break;
            case sendEventsToAPI:
                if(obj!=null) {
                    //Events has been sent to api not deleting local records
                    Integer[] ids1 = (Integer[]) obj;
                    //OFEventDBRepo.deleteEvents(mContext, ids1, this, OFConstants.ApiHitType.deleteEventsFromDB);
                    new OFEventDBRepoKT().deleteEvents(mContext, ids1, this, OFConstants.ApiHitType.deleteEventsFromDB);
                }
                break;
            case deleteEventsFromDB:
                if(obj!=null) {
                    OFOneFlowSHP.getInstance(mContext).storeValue(OFConstants.SHP_EVENTS_DELETE_PENDING,false);
                    OFHelper.v("FeedbackControler", "1Flow events delete count[" + ((Integer) obj) + "]");
                    Intent intent = new Intent("events_submitted");
                    intent.putExtra("size", String.valueOf((Integer) obj));
                    mContext.sendBroadcast(intent);
                }
                break;
        }
    }
}
