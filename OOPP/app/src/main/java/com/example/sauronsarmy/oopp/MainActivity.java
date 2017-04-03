package com.example.sauronsarmy.oopp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton homeButton;
    private ImageButton mapButton;
    private ImageButton shopButton;
    private ImageButton statsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Clicking on Home/Shop/Map/Stats should send the user to the
        appropriate activity.
         */
        homeButton  = (ImageButton) findViewById(R.id.b_home);
        mapButton   = (ImageButton) findViewById(R.id.b_map);
        statsButton = (ImageButton) findViewById(R.id.b_stats);
        shopButton  = (ImageButton) findViewById(R.id.b_shop);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Context context = MainActivity.this;
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
                        startActivity(new Intent(context, StatsActivity.class));
                        break;
                }
            }
        };
        homeButton.setOnClickListener(buttonListener);
        shopButton.setOnClickListener(buttonListener);
        mapButton.setOnClickListener(buttonListener);
        statsButton.setOnClickListener(buttonListener);
    }

}
