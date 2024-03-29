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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.oneflow.analytics.OneFlow;
import com.oneflow.analytics.model.survey.OFSurveyUserInput;
import com.oneflow.analytics.model.survey.OFSurveyUserInputKT;
import com.oneflow.analytics.repositories.OFLogUserDBRepoKT;
import com.oneflow.analytics.repositories.OFSurvey;
import com.oneflow.analytics.sdkdb.OFOneFlowSHP;



public class OFNetworkChangeReceiver extends BroadcastReceiver implements OFMyResponseHandlerOneFlow {

    public Context context;


    @Override
    public void onReceive(final Context context, final Intent intent) {

        this.context = context;


        if (OFHelper.isConnected(context)) {
            // Helper.makeText(context,"Network available",1);
            OFOneFlowSHP shp = OFOneFlowSHP.getInstance(context);
           // OFLocationResponse lr = OFOneFlowSHP.getInstance(context).getUserLocationDetails();
            if (shp.getBooleanValue(OFConstants.AUTOEVENT_FIRSTOPEN,false)) {
                if(counter<1) {
                    checkOffLineSurvey();
                }
            } else {
                String projectKey = OFOneFlowSHP.getInstance(context).getStringValue(OFConstants.APPIDSHP);
                OneFlow.configure(context, projectKey);
                // CurrentLocation.getCurrentLocation(context,this,Constants.ApiHitType.fetchLocation);
            }
        }
    }



    int counter = 0;
    public void checkOffLineSurvey() {
        OFHelper.v("OFNetworkChangeReceiver", "1Flow calling checkOffLineSurvey["+(counter++)+"] ");

        new OFLogUserDBRepoKT().fetchSurveyInput(context, this, OFConstants.ApiHitType.fetchSurveysFromDB);
        //new OFMyDBAsyncTask(context,this,OFConstants.ApiHitType.fetchSurveysFromDB).execute();

    }

    @Override
    public void onResponseReceived(OFConstants.ApiHitType hitType, Object obj, Long reserve, String reserved, Object obj2, Object obj3) {

        switch (hitType) {

            case fetchSurveysFromDB:
                if(obj!=null) {
                    OFSurveyUserInput survey = (OFSurveyUserInput) obj;
                    if (survey != null) {
                        OFSurvey.submitUserResponseOffline(context, survey, this, OFConstants.ApiHitType.logUser);
                    }
                }else{
                    counter=0;
                }
                break;
            case submittingOfflineSurvey:

                if(obj!=null) {
                    OFSurveyUserInput survey1 = (OFSurveyUserInput) obj;
                    //new OFLogUserDBRepoKT().deleteSentSurveyFromDB(context, new Integer[]{survey1.get_id()}, this, OFConstants.ApiHitType.deleteEventsFromDB);

                    new OFLogUserDBRepoKT().updateSurveyInput(context, this, OFConstants.ApiHitType.updateSurveyIds, true, survey1.getSurvey_id());

                    //new OFMyDBAsyncTask(context,this,OFConstants.ApiHitType.deleteEventsFromDB).execute(new Integer[]{survey1.get_id()});
                }
                break;
            //case deleteSurveyFromDB:
            case updateSurveyIds:
                checkOffLineSurvey();
                break;
        }
    }


}