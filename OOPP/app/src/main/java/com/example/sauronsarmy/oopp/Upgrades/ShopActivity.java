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

    Shop shop;
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

        shop = Shop.getInstance();

        shopButton.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimary));

        /**
         * Get upgrade buttons
         * Add listeners to buttons
         */
        ImageButton dmgUpgradeButton = (ImageButton) findViewById(R.id.dmgUpgradeButton);
        ImageButton multiplierUpgradeButton = (ImageButton) findViewById(R.id.mltUpgradeButton);

        /* ADD LISTENERS TO BUTTONS */
        dmgUpgradeButton.setOnClickListener(buttonListener);
        multiplierUpgradeButton.setOnClickListener(buttonListener);

        updateDamageInfo();
        updateMultiplierInfo();

    }

    void updateDamageInfo(){
        /** TextView objects */
        /* DAMAGE */
        TextView dmgCounter = (TextView) findViewById(R.id.dmgUpgradeCounter);
        TextView dmgCost = (TextView) findViewById(R.id.dmgUpgradeCost);

        TextView currentDmg = (TextView) findViewById(R.id.currentDmg);
        TextView newDmg = (TextView) findViewById(R.id.newDmg);

        /** Set the values to the view from the upgrade object
         *  Using String tmp to set text, otherwise it complains about Android resource
         *  i don't know how to do that yet.
         * */
        /* DAMAGE UPGRADE */
        Upgrade damageUpgrade = shop.getDamageUpgrade();

        dmgCost.setText(String.valueOf(damageUpgrade.getCost() + " g"));
        dmgCounter.setText(String.valueOf(shop.getDamageUpgradeCounter()));

        int dmg = PlayerModel.getInstance().getDamage();
        int newdmg = (int) damageUpgrade.getStat() + dmg;
        currentDmg.setText(String.valueOf(dmg));
        newDmg.setText(String.valueOf(newdmg));


    }

    void updateMultiplierInfo(){
        /** TextView objects */
        /* MULTIPLIER */
        TextView multiplierCounter = (TextView) findViewById(R.id.mltUpgradeCounter);
        TextView multiplierCost = (TextView) findViewById(R.id.mltUpgradeCost);

        TextView currentMlt = (TextView) findViewById(R.id.currentMlt);
        TextView newMlt = (TextView) findViewById(R.id.newMlt);

        /** Set the values to the view from the upgrade object
         *  Using String tmp to set text, otherwise it complains about Android resource
         *  i don't know how to do that yet.
         * */
        /* MULTIPLIER UPGRADE */
        Upgrade multiplierUpgrade = shop.getMultiplierUpgrade();

        multiplierCounter.setText(String.valueOf(shop.getMultiplierUpgradeCounter()));
        multiplierCost.setText(String.valueOf(multiplierUpgrade.getCost() + " g"));

        double mlt = PlayerModel.getInstance().getDamageMultiplier();
        mlt = Math.round(mlt * 100) / 100.0;
        double nMlt = mlt + multiplierUpgrade.getStat();
        nMlt = Math.round(nMlt * 100) / 100.0;
        currentMlt.setText(String.valueOf(mlt));
        newMlt.setText(String.valueOf(nMlt));

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
                    if (shop.upgradeDamage()){
                        updateDamageInfo();
                    }
                    break;
                case R.id.mltUpgradeButton:
                    if (shop.upgradeMultiplier()){
                        updateMultiplierInfo();
                    }
                    break;
            }
        }
    };
}
