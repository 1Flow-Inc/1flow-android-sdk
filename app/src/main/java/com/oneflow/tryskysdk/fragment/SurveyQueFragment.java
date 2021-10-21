package com.oneflow.tryskysdk.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.Rating;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oneflow.tryskysdk.R;
import com.oneflow.tryskysdk.SurveyActivity;
import com.oneflow.tryskysdk.adapter.SurveyOptionsAdapter;
import com.oneflow.tryskysdk.customwidgets.CustomTextView;
import com.oneflow.tryskysdk.customwidgets.CustomTextViewBold;
import com.oneflow.tryskysdk.model.survey.RatingsModel;
import com.oneflow.tryskysdk.model.survey.SurveyChoises;
import com.oneflow.tryskysdk.model.survey.SurveyScreens;
import com.oneflow.tryskysdk.repositories.Survey;
import com.oneflow.tryskysdk.utils.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.POST;

public class SurveyQueFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.survey_title)
    CustomTextViewBold surveyTitle;
    @BindView(R.id.survey_description)
    CustomTextView surveyDescription;
    @BindView(R.id.survey_options_list)
    RecyclerView surveyOptionRecyclerView;
    @BindView(R.id.ratings_not_like)
    CustomTextView ratingsNotLike;

    @BindView(R.id.ratings_full_like)
    CustomTextView ratingsFullLike;

    @BindView(R.id.submit_btn)
    CustomTextViewBold submitButton;
    @BindView(R.id.cancel_btn)
    CustomTextViewBold cancelButton;
    @BindView(R.id.option_layout)
    RelativeLayout optionLayout;


    String tag = this.getClass().getName();
    SurveyScreens surveyScreens;
    SurveyOptionsAdapter dashboardAdapter;
    Animation animation1, animation2, animation3, animation4;

    public static SurveyQueFragment newInstance(SurveyScreens ahdList) {
        SurveyQueFragment myFragment = new SurveyQueFragment();

        Bundle args = new Bundle();
        args.putSerializable("data", ahdList);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surveyScreens = (SurveyScreens) getArguments().getSerializable("data");

    }

    int i = 0;
    @Override
    public void onResume() {
        super.onResume();
        Helper.v(tag, "OneFlow OnResume");


        View[] animateViews = new View[]{surveyTitle, surveyDescription, optionLayout};


        Animation[] annim = new Animation[]{animation1, animation2, animation3};

        if (i == 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    annim[i].setFillAfter(true);
                    animateViews[i].startAnimation(annim[i]);

                }
            }, 800);

            animation1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Helper.v(tag, "OneFlow animation START [" + i + "]");
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Helper.v(tag, "OneFlow animation END[" + i + "]");
                    //
                    i++;
                    if (i < animateViews.length) {
                        animateViews[i].setVisibility(View.VISIBLE);
                        //animateViews[i].clearAnimation();
                        animateViews[i].startAnimation(annim[i]);
                    }

                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    Helper.v(tag, "OneFlow animation REPEAT[" + i + "]");
                }
            });
            animation2.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Helper.v(tag, "OneFlow animation START [" + i + "]");
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Helper.v(tag, "OneFlow animation END[" + i + "]");
                    //
                    i++;
                    if (i < animateViews.length) {
                        animateViews[i].setVisibility(View.VISIBLE);
                        //animateViews[i].clearAnimation();
                        animateViews[i].startAnimation(annim[i]);
                    }

                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    Helper.v(tag, "OneFlow animation REPEAT[" + i + "]");
                }
            });
            animation3.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Helper.v(tag, "OneFlow animation START [" + i + "]");
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Helper.v(tag, "OneFlow animation END[" + i + "]");

                    i++;
                    if (i < animateViews.length) {
                        animateViews[i].setVisibility(View.VISIBLE);
                        //animateViews[i].clearAnimation();
                        animateViews[i].startAnimation(annim[i]);
                    }

                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    Helper.v(tag, "OneFlow animation REPEAT[" + i + "]");
                }
            });
        }

           /* }
        },1100);*/
    }


    /*public void animateMyView(View v){
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1000);

        fadeIn.setFillAfter(true);
        fadeIn.setDuration(1000);

        fadeIn.setAnimationListener(animListener);
        v.startAnimation(fadeIn);


    }*/


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.survey_que_fragment, container, false);
        ButterKnife.bind(this, view);

        Helper.v(tag, "OneAxis list data[" + surveyScreens + "]");
        Helper.v(tag, "OneAxis list title[" + surveyScreens.getTitle() + "]");
        Helper.v(tag, "OneAxis list desc[" + surveyScreens.getMessage() + "]");


        animation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        animation2 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        animation3 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        animation4 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);

        surveyTitle.setText(surveyScreens.getTitle());

        if (surveyScreens.getMessage() != null) {
            surveyDescription.setText(surveyScreens.getMessage());
        } else {
            surveyDescription.setVisibility(View.GONE);
        }

        Helper.v(tag, "OneFlow input type [" + surveyScreens.getInput().getInput_type() + "][" + surveyScreens.getInput().getStars() + "]min[" + surveyScreens.getInput().getMin_val() + "][" + surveyScreens.getInput().getMax_val() + "][][][]");
        if (surveyScreens.getInput().getInput_type().contains("rating") || surveyScreens.getInput().getInput_type().contains("nps")) {
            if (surveyScreens.getInput() != null) {

                if (surveyScreens.getInput().getStars()) {
                    surveyScreens.getInput().setRatingsList(prepareRatingsList(surveyScreens.getInput().getMin_val(), surveyScreens.getInput().getMax_val()));
                    if(!surveyScreens.getInput().getInput_type().contains("nps")) {
                        ratingsNotLike.setVisibility(View.GONE);
                        ratingsFullLike.setVisibility(View.GONE);
                    }
                } else if (surveyScreens.getInput().getEmoji()) {
                    surveyScreens.getInput().setRatingsList(prepareRatingsList(surveyScreens.getInput().getMin_val(), surveyScreens.getInput().getMax_val()));
                    ratingsNotLike.setVisibility(View.GONE);
                    ratingsFullLike.setVisibility(View.GONE);
                } else {
                    //surveyOptionRecyclerView.setBackgroundResource(R.drawable.rounded_gray_rectangle);
                    surveyScreens.getInput().setRatingsList(prepareRatingsList(surveyScreens.getInput().getMin_val(), surveyScreens.getInput().getMax_val()));
                }
            }
        } else {
            ratingsNotLike.setVisibility(View.GONE);
            ratingsFullLike.setVisibility(View.GONE);
        }
        Helper.v(tag, "OneFlow input type min[" + surveyScreens.getInput().getMin_val() + "][" + surveyScreens.getInput().getMax_val() + "]");

        RecyclerView.LayoutManager mLayoutManager = null;
        if (surveyScreens.getInput().getInput_type().contains("rating") || surveyScreens.getInput().getInput_type().contains("nps")) {
            Helper.v(tag, "OneFlow gridLayout set");
            mLayoutManager = new GridLayoutManager(getActivity(), (surveyScreens.getInput().getMax_val() + 1) - surveyScreens.getInput().getMin_val());
        } else {
            if (surveyScreens.getInput().getChoices() != null) {
                if (surveyScreens.getInput().getChoices().size() > 0) {
                    Helper.v(tag, "OneFlow inputtype choices init");
                    checkBoxSelection = new ArrayList<String>();
                }
            }

            Helper.v(tag, "OneFlow linearlayout set");
            mLayoutManager = new LinearLayoutManager(getActivity());
        }

        dashboardAdapter = new SurveyOptionsAdapter(getActivity(), surveyScreens.getInput(), this, sa.themeColor);

        surveyOptionRecyclerView.setLayoutManager(mLayoutManager);
        surveyOptionRecyclerView.setItemAnimator(new DefaultItemAnimator());
        surveyOptionRecyclerView.setAdapter(dashboardAdapter);

        if (surveyScreens.getInput().getInput_type().equalsIgnoreCase("checkbox")) {
            if (surveyScreens.getButtons() != null) {
                if (surveyScreens.getButtons().size() > 0) {

                }

            }
        }
        return view;

    }

    private ArrayList<RatingsModel> prepareRatingsList(int min, int max) {
        ArrayList<RatingsModel> ratingsList = new ArrayList<>();
        RatingsModel rm = null;
        while (min <= max) {
            rm = new RatingsModel();
            rm.setId(min++);
            rm.setSelected(false);
            ratingsList.add(rm);
        }
        return ratingsList;
    }

    SurveyActivity sa;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Helper.v(tag, "OneFlow onAttach called");
        sa = (SurveyActivity) context;
        sa.position++;

    }


    @Override
    public void onClick(View v) {
        // Helper.makeText(getActivity(), "Clicked on [" + v.getTag() + "]", 1);

        switch (v.getId()) {
            case R.id.submit_btn:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (checkBoxSelection.size() > 0) {
                            String allSelections = checkBoxSelection.toString().replace("[", "");
                            allSelections = allSelections.replace("]", "");
                            allSelections = allSelections.replace(" ", "");
                            Helper.v(tag, "OneFlow allselection[" + allSelections + "]");
                            sa.addUserResponseToList(surveyScreens.get_id(), null, allSelections);
                        } else {
                            sa.initFragment();
                        }
                    }
                }, 1000);
                break;
            default:
                Helper.v(tag, "OneFlow inputtype[" + surveyScreens.getInput().getInput_type() + "]isCheckbox[" + surveyScreens.getInput().getInput_type().equalsIgnoreCase("checkbox") + "]ratings[" + surveyScreens.getInput().getInput_type().contains("rating") + "]isStar[" + surveyScreens.getInput().getStars() + "]");
                if (surveyScreens.getInput().getInput_type().contains("rating")) {
                    int position = (int) v.getTag();

                    Helper.v(tag,"OneFlow inputType["+surveyScreens.getInput().getStars()+"]position["+position+"]");
                    if (surveyScreens.getInput().getStars()) {
                        setSelected(position, false);
                    } else {
                        setSelected(position, true);
                    }
                } else if (surveyScreens.getInput().getInput_type().contains("nps")) {
                    int position = (int) v.getTag();
                    setSelected(position, true);
                } else if (surveyScreens.getInput().getInput_type().equalsIgnoreCase("mcq")) {

                    RadioButton rb = (RadioButton) v;
                    String position = (String) v.getTag();
                    Helper.v(tag, "OneFlow mcq clicked Position[" + position + "]");
                    /*if (rb.isChecked()) {
                        ((View) rb.getParent()).setBackgroundResource(R.drawable.rounded_rectangle_with_border_blue);
                    } else {
                        ((View) rb.getParent()).setBackgroundResource(R.drawable.rounded_rectangle_with_border_gray);
                    }*/
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sa.addUserResponseToList(surveyScreens.get_id(), position, null);
                        }},1000);

                } else if (surveyScreens.getInput().getInput_type().equalsIgnoreCase("checkbox")) {
                    Helper.v(tag, "OneFlow inside checkbox");
                    CheckBox cb = (CheckBox) v;
                    Helper.v(tag, "OneFlow inside checkbox 1");
                    String viewTag = (String) cb.getTag();
                    Helper.v(tag, "OneFlow inside checkbox tag[" + viewTag + "]isChecked[" + cb.isChecked() + "]");
                    /*if (cb.isChecked()) {
                        ((View) cb.getParent()).setBackgroundResource(R.drawable.rounded_rectangle_with_border_blue);
                    } else {
                        ((View) cb.getParent()).setBackgroundResource(R.drawable.rounded_rectangle_with_border_gray);
                    }*/
                    checkBoxSelectionStatus(viewTag, cb.isChecked());
                }
                break;
        }


    }


    private void setSelected(int position, Boolean isSingle) {
        int i = 0;
        Helper.v(tag,"OneFlow position ["+position+"]isSingle["+isSingle+"]");
        while (i < surveyScreens.getInput().getRatingsList().size()) {
            if (isSingle) {
                surveyScreens.getInput().getRatingsList().get(i).setSelected(false);
            } else {
                if (i <= position) {
                    surveyScreens.getInput().getRatingsList().get(i).setSelected(true);
                } else {
                    surveyScreens.getInput().getRatingsList().get(i).setSelected(false);
                }
            }
            i++;
        }
        if (isSingle) {
            if(surveyScreens.getInput().getInput_type().equalsIgnoreCase("nps") || surveyScreens.getInput().getInput_type().equalsIgnoreCase("rating-numerical")){
            for(RatingsModel rm: surveyScreens.getInput().getRatingsList()){
                if(rm.getId()==position){
                    rm.setSelected(true);
                }
            }}else {
                surveyScreens.getInput().getRatingsList().get(position).setSelected(true);
            }

        }
        dashboardAdapter.notifyMyList(surveyScreens.getInput());
        if (submitButton.getVisibility() != View.VISIBLE) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    sa.addUserResponseToList(surveyScreens.get_id(), String.valueOf(position), null);
                }
            }, 1000);
        }
    }

    ArrayList<String> checkBoxSelection;

    private void checkBoxSelectionStatus(String tag, Boolean isCheck) {


        if (isCheck) { // adding value in the list
            checkBoxSelection.add(tag);
        } else { // removing value from the list
            checkBoxSelection.remove(tag);
        }


        Helper.v(tag, "OneFlow button size found[" + checkBoxSelection.size() + "]");
        if (checkBoxSelection.size() > 0) {
            if (surveyScreens.getButtons() != null) {
                if (surveyScreens.getButtons().size() == 1) {
                    submitButton.setText(surveyScreens.getButtons().get(0).getTitle());
                    submitButton.setVisibility(View.VISIBLE);
                    submitButton.setOnClickListener(this);
                } else if (surveyScreens.getButtons().size() == 2) {
                    submitButton.setText(surveyScreens.getButtons().get(0).getTitle());
                    submitButton.setVisibility(View.VISIBLE);
                    submitButton.setOnClickListener(this);
                    cancelButton.setText(surveyScreens.getButtons().get(1).getTitle());
                    cancelButton.setVisibility(View.VISIBLE);
                    cancelButton.setOnClickListener(this);
                }
            }
        } else {
            //In case of selection reverted
            submitButton.setVisibility(View.INVISIBLE);
            cancelButton.setVisibility(View.INVISIBLE);
        }


    }

}
