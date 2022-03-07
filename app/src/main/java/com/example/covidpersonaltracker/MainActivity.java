package com.example.covidpersonaltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;

import com.example.covidpersonaltracker.databinding.ActivityMainBinding;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Activity activity;
    String language="en";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Code to Change Status color (Replaced by attribute in themes.xml)
//        Window window=this.getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.setStatusBarColor(this.getResources().getColor(R.color.status_bar));
        activity=this;

        //Saving user to Sharepreference
        binding.goahead.setOnClickListener(item->{
            if(binding.edtEmail.getText()!=null&&binding.edtName.getText()!=null){
                Utils.saveSharedPreference(activity,"Settings","name",binding.edtName.getText().toString());
                Utils.saveSharedPreference(activity,"Settings","email",binding.edtEmail.getText().toString());
                Utils.saveSharedPreference(activity,"Settings","lang",language);
                Utils.saveSharedPreference(activity,"Settings","loggedIn","1");
            }
            Intent i=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(i);
            finish();
        });

        //Language Change
        binding.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    language="hi";
                    setLanguage(activity,"hi");
                }else{
                    language="en";
                    setLanguage(activity,"en");
                }
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