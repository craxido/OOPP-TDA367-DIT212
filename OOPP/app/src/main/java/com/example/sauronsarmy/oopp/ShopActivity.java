package com.example.sauronsarmy.oopp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ShopActivity extends AppCompatActivity {

    Shop shop = Shop.getInstance();
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

        /** Objects in the damage upgrade button*/
        ImageButton dmgUpgradeButton = (ImageButton) findViewById(R.id.dmgUpgradeButton);

        /** objects in the multiplier upgrade button */
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

        /** Set the values to the view from the upgrade object
         *  Using String tmp to set text, otherwise it complains about Android resource
         *  i don't know how to do that yet.
         * */
        /* DAMAGE UPGRADE */
        Upgrade damageUpgrade = shop.getDamageUpgrade();
        String tmp = damageUpgrade.getCurrentCost() + " g";
        dmgCost.setText(tmp);
        tmp = shop.getDamageUpgradeCounter() + "";
        dmgCounter.setText(tmp);

    }

    void updateMultiplierInfo(){
        /** TextView objects */
        /* MULTIPLIER */
        TextView multiplierCounter = (TextView) findViewById(R.id.mltUpgradeCounter);
        TextView multiplierCost = (TextView) findViewById(R.id.mltUpgradeCost);

        /** Set the values to the view from the upgrade object
         *  Using String tmp to set text, otherwise it complains about Android resource
         *  i don't know how to do that yet.
         * */
        /* MULTIPLIER UPGRADE */
        Upgrade multiplierUpgrade = shop.getMultiplierUpgrade();
        String tmp = shop.getMultiplierUpgradeCounter() + "";
        multiplierCounter.setText(tmp);
        tmp = multiplierUpgrade.getCurrentCost() + " g";
        multiplierCost.setText(tmp);


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
                    if (shop.buyDamageUpgrade()){
                        updateDamageInfo();
                    }
                    break;
                case R.id.mltUpgradeButton:
                    if (shop.buyMultiplierUpgrade()){
                        updateMultiplierInfo();
                    }
                    break;
            }
        }
    };
}
