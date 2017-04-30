package com.example.sauronsarmy.oopp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sauronsarmy.oopp.Stats.StatsActivity;

/*
* Created by Filip Labe 2017-04-30
*
* */



public class WelcomeActivity extends AppCompatActivity implements MainMVPInterface.ViewOps{
    private static final String TAG ="WelcomeActivity";
    private MainMVPInterface.PresenterOps mainPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG,"onCreate() called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        Button loadGame = (Button) findViewById(R.id.loadGame);
        Button newGame = (Button) findViewById(R.id.newGame);

        loadGame.setOnClickListener(buttonListener);
        newGame.setOnClickListener(buttonListener);
    }


    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // Figure out which button was pressed

            switch (v.getId()){

                case R.id.newGame:
                    newGame();
                    break;
                case R.id.loadGame:
                    loadGame();
                    break;
            }



        }
    };



    private void loadGame(){
        mainPresenter = new MainPresenter(this);

        // Load previous state
        Log.i(TAG, "Will attempt to load previous state if there is one");
        mainPresenter.loadState(WelcomeActivity.this);

        Context context = WelcomeActivity.this;
        startActivity(new Intent(context, MainActivity.class));


    }

    private void newGame(){
        Context context = WelcomeActivity.this;
        startActivity(new Intent(context, MainActivity.class));


    }
}
