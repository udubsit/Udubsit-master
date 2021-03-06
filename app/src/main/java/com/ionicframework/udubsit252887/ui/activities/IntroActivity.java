package com.ionicframework.udubsit252887.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.ionicframework.udubsit252887.ui.fragments.IntroFragment1;
import com.ionicframework.udubsit252887.ui.fragments.IntroFragment2;
import com.ionicframework.udubsit252887.ui.fragments.IntroFragment3;
import com.ionicframework.udubsit252887.ui.fragments.IntroFragment4;

/**
 * Created by Test on 14/03/2017.
 */

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Add slides to fragment
        //Appintro will automaticall generate the dots indicatior and buttons
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

                //Create new booeand and preference and set it to true
                boolean isFirstStart = getPrefs.getBoolean("firstStart",true);


                //if the activity has never started before
                if(isFirstStart)
                {
//                    Intent i = new Intent(MainActivity.this,IntroActivity.class);
                    addSlide(IntroFragment1.newInstance());
                    addSlide(IntroFragment2.newInstance());
                    addSlide(IntroFragment3.newInstance());
                    addSlide(IntroFragment4.newInstance());
                    showStatusBar(false);

                    showSkipButton(true);

                    //make new preference editor
                    SharedPreferences.Editor e = getPrefs.edit();

                    //Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firststart",false);

                    e.apply();

                }
            }
        });

        t.start();



    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Toast.makeText(this,"New Intro",Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);

        if(pager.getCurrentItem()==2) {
            pager.setCurrentItem(3);
            Toast.makeText(this,"Pager triggered",Toast.LENGTH_LONG).show();
        }
        else {
            setProgressButtonEnabled(true);
        }


    }
}
