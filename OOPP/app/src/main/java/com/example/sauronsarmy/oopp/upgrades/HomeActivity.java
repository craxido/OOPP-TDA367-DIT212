package com.example.sauronsarmy.oopp.upgrades;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sauronsarmy.oopp.MainActivity;
import com.example.sauronsarmy.oopp.map.MapActivity;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.R;
import com.example.sauronsarmy.oopp.stats.StatsActivity;

/**
 * Created by Erik on 04/04/17.
 * Written by bunnyfiscuit
 */
public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    HomeMVPInterface.Presenter homePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        homePresenter = new HomePresenter();

        homeButton.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimary));

        /**
         * Get upgrade buttons
         * Add listeners to buttons
         */
        ImageButton oilUpgradeButton = (ImageButton) findViewById(R.id.oilUpgradeButton);
        oilUpgradeButton.setOnClickListener(buttonListener);

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

        double mps = PlayerModel.getInstance().getMoneyPerSecond();
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
            Context context = HomeActivity.this;
            // Figure out which button was pressed
            switch (v.getId()) {
                case R.id.b_home:
                    break;
                case R.id.b_map:
                    startActivity(new Intent(context, MapActivity.class));
                    break;
                case R.id.b_shop:
                    startActivity(new Intent(context, ShopActivity.class));
                    break;
                case R.id.b_stats:
                    startActivity(new Intent(context, StatsActivity.class));
                    break;
                case R.id.b_main:
                    startActivity(new Intent(context, MainActivity.class));
                    break;
                case R.id.oilUpgradeButton:
                    if (homePresenter.buyOilPumpUpgrade()){
                        updateOilInfo();
                    }
                    break;
            }
        }
    };

}