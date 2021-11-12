package com.oneflow.analytics;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oneflow.analytics.adapter.SurveyListAdapter;
import com.oneflow.analytics.model.survey.GetSurveyListResponse;
import com.oneflow.analytics.sdkdb.OneFlowSHP;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;

public class SurveyList extends SDKBaseActivity {

    RecyclerView listOfSurvey;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_survey_list);
        ButterKnife.bind(this);


        listOfSurvey = (RecyclerView) findViewById(R.id.list_of_survey);

        ArrayList<GetSurveyListResponse> slr = new OneFlowSHP(this).getSurveyList();
        SurveyListAdapter addb = new SurveyListAdapter(this, slr, clickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listOfSurvey.setLayoutManager(linearLayoutManager);
        listOfSurvey.setAdapter(addb);



    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tag = (String) v.getTag();

            //GetSurveyListResponse surveyItem = checkSurveyTitleAndScreens(tag);

            HashMap<String,String> mapvalues = new HashMap<String, String>();
            mapvalues.put("testKey1","testValue1");
            mapvalues.put("testKey2","testValue2");
            mapvalues.put("testKey3","testValue3");
            OneFlow.recordEvents(tag,mapvalues);

            /*Intent intent = new Intent(SurveyList.this, SurveyActivity.class);
            intent.putExtra("SurveyType", surveyItem);
            startActivity(intent);*/
        }
    };
}