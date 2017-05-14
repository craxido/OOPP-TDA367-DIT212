package com.example.sauronsarmy.oopp.stats;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sauronsarmy.oopp.upgrades.HomeActivity;
import com.example.sauronsarmy.oopp.MainActivity;
import com.example.sauronsarmy.oopp.MainPresenter;
import com.example.sauronsarmy.oopp.map.MapActivity;
import com.example.sauronsarmy.oopp.R;
import com.example.sauronsarmy.oopp.upgrades.ShopActivity;
import com.example.sauronsarmy.oopp.clock.ClockListener;

public class StatsActivity extends AppCompatActivity implements ClockListener {

    private TextView damageText;
    private TextView dpsText;
    private TextView moneyText;
    private TextView moneyPerSecText;
    private StatsPresenterInterface statsPresenter;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        statsPresenter = new StatsPresenter();

        /*
        Clicking on Home/Shop/Map/Stats should send the user to the
        appropriate activity.
         */
        ImageButton homeButton  = (ImageButton) findViewById(R.id.b_home);
        ImageButton mapButton   = (ImageButton) findViewById(R.id.b_map);
        ImageButton statsButton = (ImageButton) findViewById(R.id.b_stats);
        ImageButton shopButton  = (ImageButton) findViewById(R.id.b_shop);
        ImageButton mainButton  = (ImageButton) findViewById(R.id.b_main);

        homeButton.setOnClickListener(buttonListener);
        shopButton.setOnClickListener(buttonListener);
        mapButton.setOnClickListener(buttonListener);
        statsButton.setOnClickListener(buttonListener);
        mainButton.setOnClickListener(buttonListener);
        statsButton.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimary));


        update();

        MainPresenter.getInstance().getRun().register(this);

    }

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Context context = StatsActivity.this;
            // Figure out which button was pressed
            switch (v.getId()) {
                case R.id.b_home:
                    startActivity(new Intent(context, HomeActivity.class));
                    break;
                case R.id.b_map:
                    startActivity(new Intent(context, MapActivity.class));
                    break;
                case R.id.b_shop:
                    startActivity(new Intent(context, ShopActivity.class));
                    break;
                case R.id.b_stats:
                    break;
                case R.id.b_main:
                    startActivity(new Intent(context, MainActivity.class));
                    break;
            }
        }
    };


    @Override
    protected void onPause(){

        //Register to clock
        MainPresenter.getInstance().getRun().unregister(this);
        super.onPause();
    }

    @Override
    protected void onStart(){
        //Register to clock
        MainPresenter.getInstance().getRun().register(this);

        super.onStart();
    }


    //Update the fields with current values
    @Override
    public void update() {
           /*
             Setting all the textViews to display the correct stats.
            */
        damageText      = (TextView) findViewById(R.id.damageText);
        dpsText         = (TextView) findViewById(R.id.dpsText);
        moneyText       = (TextView) findViewById(R.id.moneyText);
        moneyPerSecText = (TextView) findViewById(R.id.moneyperSecText);

        damageText.setText(String.valueOf(statsPresenter.getPlayerDamage()));
        dpsText.setText(String.valueOf(statsPresenter.getPlayerDamagePerSecond()));
        moneyText.setText(String.valueOf(statsPresenter.getMoneyAmount()));
        moneyPerSecText.setText(String.valueOf(statsPresenter.getMoneyPerSecond()));

    }
}
