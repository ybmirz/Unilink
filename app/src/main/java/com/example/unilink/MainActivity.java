package com.example.unilink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;

import android.content.Intent;

import android.os.Handler;
import android.view.View;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {


    // onCreate refers to a method that fires when the app is *created*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //forces light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //configuring window to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set first page
        setContentView(R.layout.activity_main);
        // basically, hold the layout main page for 1.5 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);    
                finish();        
            }
        }, 1500);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    // onStart method is called when the activity enters the Started state
    protected void onStart(Bundle savedInstanceState) {

    }

    // onResume method is called just before the activity starts an interaction with the user (button not pressable but rendered)
    // most of an app's functionality should be here
    protected void onResume(Bundle savedInstanceState) {

    }
}