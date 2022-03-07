package com.example.covidpersonaltracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.Locale;

public class SplashScreen extends AppCompatActivity {

    MotionLayout motionLayout;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        motionLayout=findViewById(R.id.motion_layout);
        activity=this;
        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                //Logic to open Home/Login Screen according to SP
                if(Utils.getSharedPreferences(activity,"Settings","loggedIn").equals("1")){
                    //Initialise Language
                    setLanguage(activity, Utils.getSharedPreferences(activity,"Settings","lang"));
                    Intent i=new Intent(SplashScreen.this,HomeActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i=new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

            }
        });
    }
    public void setLanguage(Activity activity, String languageKey){
        Locale locale=new Locale(languageKey);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        activity.getResources().updateConfiguration(configuration,activity.getResources().getDisplayMetrics());
    }
}