package com.example.sauronsarmy.oopp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                Intent intent = new Intent(context, MapActivity.class);
                startActivity(intent);
            }
        });
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                Intent intent = new Intent(context, ShopActivity.class);
                startActivity(intent);
            }
        });
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                Intent intent = new Intent(context, StatsActivity.class);
                startActivity(intent);
            }
        });
    }

}
