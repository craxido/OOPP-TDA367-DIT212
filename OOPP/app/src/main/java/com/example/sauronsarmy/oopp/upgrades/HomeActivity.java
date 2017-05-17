package com.example.sauronsarmy.oopp.upgrades;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sauronsarmy.oopp.clock.Runner;
import com.example.sauronsarmy.oopp.R;
import com.example.sauronsarmy.oopp.clock.ClockListener;

/**
 * Created by Erik on 04/04/17.
 * Written by bunnyfiscuit
 */
public class HomeActivity extends AppCompatActivity implements ClockListener {

    private static final String TAG = "HomeActivity";
    private Intent intent = new Intent();
    HomeMVPInterface.Presenter homePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenter();
        homePresenter.loadState(HomeActivity.this);
        Runner runner = Runner.getInstance();
        setContentView(R.layout.activity_home);

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


        homeButton.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimary));

        /**
         * Get upgrade buttons
         * Add listeners to buttons
         */
        ImageButton oilUpgradeButton = (ImageButton) findViewById(R.id.oilUpgradeButton);
        oilUpgradeButton.setOnClickListener(buttonListener);
        runner.register(this);

        TextView moneyIndi = (TextView) findViewById(R.id.moneyIndicator);
        moneyIndi.setText(String.valueOf(homePresenter.getPlayerMoney()));
        updateOilInfo();

    }

    void updateOilInfo(){
        /** TextView objects */
        TextView oilUpgradeCounter = (TextView) findViewById(R.id.oilUpgradeCounter);
        TextView oilUpgradeCost = (TextView) findViewById(R.id.oilUpgradeCost);
        TextView currentMps = (TextView) findViewById(R.id.currentMps);
        TextView newMps = (TextView) findViewById(R.id.newMps);


        // Set the values to the view from the upgrade object

        Upgrade oilPumpUpgrade = homePresenter.getOilPumpUpgrade();

        oilUpgradeCounter.setText(String.valueOf(homePresenter.getOilPumpUpgradeCounter()));
        oilUpgradeCost.setText(String.valueOf(oilPumpUpgrade.getCost() + " g"));

        double mps = homePresenter.getPlayerMoneyPerSec();
        double newmps = mps + oilPumpUpgrade.getStat();

        currentMps.setText(String.valueOf(mps));
        newMps.setText(String.valueOf(newmps));


    }

    @Override
    protected void onStop() {
        homePresenter.saveState(HomeActivity.this);
        super.onStop();
    }

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Figure out which button was pressed
            switch (v.getId()) {
                case R.id.b_home:
                    break;
                case R.id.b_map:
                    intent.setAction("android.intent.action.MAP");
                    startActivity(intent);
                    break;
                case R.id.b_shop:
                    intent.setAction("android.intent.action.SHOP");
                    startActivity(intent);
                    break;
                case R.id.b_stats:
                    intent.setAction("android.intent.action.STATS");
                    startActivity(intent);
                    break;
                case R.id.b_main:
                    intent.setAction("android.intent.action.MAINSCREEN");
                    startActivity(intent);
                    break;
                case R.id.oilUpgradeButton:
                    if (homePresenter.buyOilPumpUpgrade()){
                        updateOilInfo();
                    }
                    break;
            }
        }
    };

    @Override
    public void update() {
        TextView moneyIndicator = (TextView) findViewById(R.id.moneyIndicator);
        moneyIndicator.setText(String.valueOf(homePresenter.getPlayerMoney()));
    }
}
