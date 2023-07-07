package com.oneflow.analytics.utils;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;

public class OFDelayedSurveyCountdownTimer extends CountDownTimer{

    Context mContext;
    static OFDelayedSurveyCountdownTimer cdt;
    static int count = 0;
    Intent intent1;
    public static synchronized OFDelayedSurveyCountdownTimer getInstance(Context context, Long duration, Long interval, Intent intent) {
        if (cdt == null) {
            cdt = new OFDelayedSurveyCountdownTimer(context, duration, interval,intent);
        }
        return cdt;
    }
    private OFDelayedSurveyCountdownTimer(Context context, Long duration, Long interval, Intent delayedIntent) {
        super(duration, interval);
        this.mContext = context;
        this.intent1 = delayedIntent;
       // OFHelper.v("OFLogCountdownTimer", "OneFlow LogApi Constructor called");
    }
    @Override
    public void onTick(long millisUntilFinished) {
       OFHelper.v("OFDelayedSurveyCountdownTimer", "1Flow waiting for survey to start");

    }

    @Override
    public void onFinish() {
        OFHelper.v("OFDelayedSurveyCountdownTimer", "1Flow init survey");
        mContext.getApplicationContext().startActivity(intent1);
    }



}