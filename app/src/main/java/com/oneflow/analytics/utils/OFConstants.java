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

public interface OFConstants {



    String currentVersion = "2023.03.08";//2023.01.12";//""2022.11.20";
    String MODE = "prod";//"dev";//"

    String DBNAME = "one_flow_db";
    String APPKEYSHP = "one_flow_config_key";
    String APPIDSHP = "one_flow_app_id_key";
    String USERDETAILSHP = "one_flow_user_detail_key";
    String USERUNIQUEIDSHP = "one_flow_user_unique_id_key";
    String LOGUSERREQUESTSHP = "one_flow_log_user_detail_key";
    String USERLOCATIONDETAILSHP = "one_flow_user_location_detail_key";
    //String SESSIONDETAIL_IDSHP = "one_flow_session_detail_id_key";
    //String SESSIONDETAIL_SYSTEM_IDSHP = "one_flow_session_detail_system_key";
    String SURVEYLISTSHP = "one_flow_survey_list_key";
    String SURVEYCLOSEDLISTSHP = "one_flow_survey_closed_list_key";
    String SDKVERSIONSHP = "sdk_version_key";
    String BRACTION_EVENTS = "one_flow_submit_events";
    String BRACTION_SURVEYS = "one_flow_submit_surveys";
    String AUTOEVENT_FIRSTOPEN = "first_open"; //Used for sharedpref also
    String AUTOEVENT_APPUPDATE = "app_updated";
    String AUTOEVENT_SESSIONSTART = "session_start";
    String AUTOEVENT_INAPP_PURCHASE = "in_app_purchase";
    String AUTOEVENT_SURVEYIMPRESSION = "survey_impression";
    String AUTOEVENT_CLOSED_SURVEY = "$flow_closed";

    String SHP_SURVEYSTART = "survey_starts";
    String SHP_ONEFLOW_CONFTIMING = "shp_conf_timing";
    String SHP_SURVEY_RUNNING = "survey_running";
    String SHP_SURVEY_FETCH_TIME = "survey_fetch_time";
    String SHP_SHOULD_SHOW_SURVEY = "should_show_survey";
    String SHP_DEVICE_UNIQUE_ID = "device_unique_id";
    String SHP_SHOULD_PRINT_LOG = "should_print_log";
    String SHP_LOG_USER_KEY = "log_user_key";
    String SHP_NETWORK_LISTENER = "network_listener";
    String SHP_TIMER_LISTENER = "timer_listener";
    String SHP_THROTTLING_KEY = "throttling_key";
    String SHP_THROTTLING_RECEIVER = "throttling_receiver";
    String SHP_THROTTLING_TIME = "throttling_receiver_time";
    String SHP_SURVEY_SEARCH_POSITION = "survey_search_position";
    String SHP_LAST_CLICK_TIME = "survey_last_click_time";
    String SHP_EVENTS_DELETE_PENDING = "survey_events_delete_pending";



    String surveyDetail = "surveyDetail";

    String os = "android";



    enum ApiHitType{

        Config, FirstOpen, CreateUser, CreateSession, RecordLogs, fetchEventsFromDBBeforeConfig,fetchEventsFromDB, sendEventsToAPI, insertEventsInDB,
        deleteEventsFromDB,deleteEventsFromDBLastSession, submittingOfflineSurvey, logUser, insertSurveyInDB, fetchSurveysFromDB, deleteSurveyFromDB, fetchLocation,

        fetchSurveysFromAPI,fetchEventsBeforSurveyFetched,fetchSubmittedSurvey,checkResurveyNSubmission,updateSurveyIds,
        surveySubmited,lastSubmittedSurvey,updateSubmittedSurveyLocally
    }
    enum BRActionType{
        submitEvents,submitSurveys
    }

    String userInputValueTemp = "";
    int screenWidth = 1100;
}
