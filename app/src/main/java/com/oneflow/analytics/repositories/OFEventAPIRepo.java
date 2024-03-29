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

package com.oneflow.analytics.repositories;

import com.oneflow.analytics.model.OFApiInterface;
import com.oneflow.analytics.model.OFGenericResponse;
import com.oneflow.analytics.model.OFRetroBaseService;
import com.oneflow.analytics.model.events.OFEventAPIRequest;
import com.oneflow.analytics.utils.OFConstants;
import com.oneflow.analytics.utils.OFHelper;
import com.oneflow.analytics.utils.OFMyResponseHandlerOneFlow;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OFEventAPIRepo {
    static String tag = "EventAPIRepo";
    public static void sendLogsToApi(String headerKey, OFEventAPIRequest ear, OFMyResponseHandlerOneFlow mrh, OFConstants.ApiHitType type, Integer []ids){

        //OFHelper.v(tag,"OneFlow sendLogsToApi reached");
        OFApiInterface connectAPI = OFRetroBaseService.getClient().create(OFApiInterface.class);
        try {
            Call<OFGenericResponse> responseCall = null;


            String url = "https://us-west-2.aws.webhooks.mongodb-realm.com/api/client/v2.0/app/1flow-wslxs/service/events-bulk/incoming_webhook/insert-events";
            responseCall = connectAPI.uploadAllUnSyncedEvents(headerKey,ear);//,url);

            responseCall.enqueue(new Callback<OFGenericResponse>() {
                @Override
                public void onResponse(Call<OFGenericResponse> call, Response<OFGenericResponse> response) {
                   /* OFHelper.v(tag, "OneFlow sendLogsToApi reached success["+response.isSuccessful()+"]");
                    OFHelper.v(tag, "OneFlow sendLogsToApi reached success raw["+response.raw()+"]");
                    OFHelper.v(tag, "OneFlow sendLogsToApi reached success errorBody["+response.errorBody()+"]");
                    OFHelper.v(tag, "OneFlow sendLogsToApi reached success message["+response.message()+"]");*/


                    if (response.isSuccessful()) {
                       // OFHelper.v(tag,"OneFlow response["+response.body().toString()+"]");
                        //OFHelper.v(tag,"OneFlow response["+response.body().getSuccess()+"]");
                        mrh.onResponseReceived(type,ids,0l,"",null,null);
                    } else {
                        OFHelper.v(tag,"OneFlow response 0["+response.body()+"]");
                    }
                }

                @Override
                public void onFailure(Call<OFGenericResponse> call, Throwable t) {


                    OFHelper.e(tag,"OneFlow error["+t.toString()+"]");
                    OFHelper.e(tag,"OneFlow errorMsg["+t.getMessage()+"]");

                }
            });
        } catch (Exception ex) {

        }

    }
}
