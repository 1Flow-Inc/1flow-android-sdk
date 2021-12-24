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

package com.oneflow.analytics.model.loguser;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.oneflow.analytics.model.survey.OFSurveyUserInput;

@Dao
public interface OFLogDAO {

    @Insert
    public void insertUserInput(OFSurveyUserInput sur);

    @Query("select * from SurveyUserInput LIMIT 1")
    OFSurveyUserInput getOfflineUserInput();

    @Query("select * from SurveyUserInput where survey_id = :surveyId and user_id = :userId")
    OFSurveyUserInput getSurveyForID(String surveyId, String userId);

    @Query("update SurveyUserInput set synced = :syncNew where _id = :id")
    int updateUserInput(Boolean syncNew,Integer id);

    @Query("update SurveyUserInput set user_id = (:userId) where user_id = 'NA'")
    int updateUserID(String userId);

    /*@Query("select * from SurveyUserInput")
    ArrayList<SurveyUserInput> getOfflineUserInputList();*/

    @Query("Delete from SurveyUserInput where _id in (:idList)")
    Integer deleteSurvey(Integer[] idList);



}