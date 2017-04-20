package com.example.sauronsarmy.oopp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

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
        EditText dmgCounter = (EditText) findViewById(R.id.dmgUpgradeCounter);
        EditText dmgCost = (EditText) findViewById(R.id.dmgUpgradeCost);

        /** objects in the multiplier upgrade button */
        ImageButton multiplierUpgradeButton = (ImageButton) findViewById(R.id.mltUpgradeButton);
        EditText multiplierCounter = (EditText) findViewById(R.id.mltUpgradeCounter);
        EditText multiplierCost = (EditText) findViewById(R.id.mltUpgradeCost);


        /** set the values to the view from the upgrade object*/
        /* DAMAGE UPGRADE */
        Upgrade damageUpgrade = shop.getDamageUpgrade();
      //  ((EditText) findViewById(R.id.dmgUpgradeCounter)).setText(shop.getDamageUpgradeCounter() + "");
        dmgCost.setText(damageUpgrade.getCurrentCost() + "");
        dmgCounter.setText(damageUpgrade.getCurrentCost() + " g");


        /* MULTIPLIER UPGRADE */
        Upgrade multiplierUpgrade = shop.getMultiplierUpgrade();
        multiplierCounter.setText(shop.getMultiplierUpgradeCounter() + "");
        multiplierCost.setText(multiplierUpgrade.getCurrentCost() + " g ");

        /* ADD LISTENERS TO BUTTONS */
        dmgUpgradeButton.setOnClickListener(buttonListener);
        multiplierUpgradeButton.setOnClickListener(buttonListener);


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
                    shop.buyDamageUpgrade();
                    break;
                case R.id.mltUpgradeButton:
                    shop.buyMultiplierUpgrade();
                    break;
            }
        }
    };
}
