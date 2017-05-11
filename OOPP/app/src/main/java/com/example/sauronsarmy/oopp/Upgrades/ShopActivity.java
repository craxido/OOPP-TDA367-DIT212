package com.example.sauronsarmy.oopp.Upgrades;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sauronsarmy.oopp.MainActivity;
import com.example.sauronsarmy.oopp.Map.MapActivity;
import com.example.sauronsarmy.oopp.Player.PlayerModel;
import com.example.sauronsarmy.oopp.R;
import com.example.sauronsarmy.oopp.Stats.StatsActivity;


/**
 * Created by Erik on 04/04/17.
 * Written by bunnyfiscuit
 */

public class ShopActivity extends AppCompatActivity {

    private ShopMVPInterface.Presenter shopPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
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

        shopPresenter = new ShopPresenter();

        shopButton.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimary));

        /**
         * Get upgrade buttons
         * Add listeners to buttons
         */
        ImageButton dmgUpgradeButton = (ImageButton) findViewById(R.id.dmgUpgradeButton);
        ImageButton dpsUpgradeButton = (ImageButton) findViewById(R.id.dpsUpgradeButton);

        /* ADD LISTENERS TO BUTTONS */
        dmgUpgradeButton.setOnClickListener(buttonListener);
        dpsUpgradeButton.setOnClickListener(buttonListener);

        updateDamageInfo();
        updateDPSInfo();

    }

    void updateDamageInfo(){
        /** TextView objects */
        /* DAMAGE */
        TextView dmgCounter = (TextView) findViewById(R.id.dmgUpgradeCounter);
        TextView dmgCost = (TextView) findViewById(R.id.dmgUpgradeCost);

        TextView currentDmg = (TextView) findViewById(R.id.currentDmg);
        TextView newDmg = (TextView) findViewById(R.id.newDmg);

        /** Set the values to the view from the upgrade object
         *
         * */
        /* DAMAGE UPGRADE */
        Upgrade damageUpgrade = shopPresenter.getDamageUpgrade();

        dmgCost.setText(String.valueOf(damageUpgrade.getCost() + " g"));
        dmgCounter.setText(String.valueOf(shopPresenter.getDamageUpgradeCounter()));

        int dmg = PlayerModel.getInstance().getDamage();
        int newdmg = damageUpgrade.getStat() + dmg;

        currentDmg.setText(String.valueOf(dmg));
        newDmg.setText(String.valueOf(newdmg));


    }

    void updateDPSInfo(){
        /** TextView objects */
        /* DAMAGE PER SECOND */
        TextView dpsCounter = (TextView) findViewById(R.id.dpsUpgradeCounter);
        TextView dpsCost = (TextView) findViewById(R.id.dpsUpgradeCost);

        TextView currentDPS = (TextView) findViewById(R.id.currentDPS);
        TextView newDPS = (TextView) findViewById(R.id.newDPS);

        // Set the values to the view from the upgrade object
        /* DPS UPGRADE */
        Upgrade dpsUpgrade = shopPresenter.getDPSUpgrade();

        dpsCounter.setText(String.valueOf(shopPresenter.getDPSUpgradeCounter()));
        dpsCost.setText(String.valueOf(dpsUpgrade.getCost() + " g"));

        int dps = PlayerModel.getInstance().getDamagePerSecond();
        int nDps = dps + dpsUpgrade.getStat();

        currentDPS.setText(String.valueOf(dps));
        newDPS.setText(String.valueOf(nDps));

    }

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Context context = ShopActivity.this;
            // Figure out which button was pressed
            switch (v.getId()) {
                case R.id.b_home:
                    startActivity(new Intent(context, HomeActivity.class));
                    break;
                case R.id.b_map:
                    startActivity(new Intent(context, MapActivity.class));
                    break;
                case R.id.b_shop:
                    break;
                case R.id.b_stats:
                    startActivity(new Intent(context, StatsActivity.class));
                    break;
                case R.id.b_main:
                    startActivity(new Intent(context, MainActivity.class));
                    break;
                case R.id.dmgUpgradeButton:
                    if (shopPresenter.buyDamageUpgrade()){
                        updateDamageInfo();
                    }
                    break;
                case R.id.dpsUpgradeButton:
                    if (shopPresenter.buyDPSUpgrade()){
                        updateDPSInfo();
                    }
                    break;
            }
        }
    };
}
