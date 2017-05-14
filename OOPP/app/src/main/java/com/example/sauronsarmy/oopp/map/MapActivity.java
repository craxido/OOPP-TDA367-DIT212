package com.example.sauronsarmy.oopp.map;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.sauronsarmy.oopp.upgrades.HomeActivity;
import com.example.sauronsarmy.oopp.MainActivity;
import com.example.sauronsarmy.oopp.R;
import com.example.sauronsarmy.oopp.upgrades.ShopActivity;
import com.example.sauronsarmy.oopp.stats.StatsActivity;

public class MapActivity extends AppCompatActivity implements MapMVPInterface.ViewOps {

    private static MapMVPInterface.PresenterOps mapPresenter = MapPresenter.getInstance();
    private static final String TAG = "MapActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
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

        /* Area buttons. Clicking on an area should send the user to that area of levels */
        ImageButton area1Button  = (ImageButton) findViewById(R.id.b_area1);
        ImageButton area2Button  = (ImageButton) findViewById(R.id.b_area2);
        ImageButton area3Button  = (ImageButton) findViewById(R.id.b_area3);
        area1Button.setOnClickListener(buttonListener);
        area2Button.setOnClickListener(buttonListener);
        area3Button.setOnClickListener(buttonListener);

        mapButton.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimary));

    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy() called");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop() called");
        super.onStop();
    }

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Context context = MapActivity.this;
            // Figure out which button was pressed
            switch (v.getId()) {
                case R.id.b_home:
                    startActivity(new Intent(context, HomeActivity.class));
                    break;
                case R.id.b_map:
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
                /* TODO: Selecting a new area should result in changing the monsters/ levels
                 * TODO: as well as the background in the MainActivity.
                    */
                case R.id.b_area1: 
                    MapPresenter.setBackgroundRef(mapPresenter.getArea(0).getImgRef());
                    break;
                case R.id.b_area2:
                    MapPresenter.setBackgroundRef(mapPresenter.getArea(1).getImgRef());
                    break;
                case R.id.b_area3:
                    MapPresenter.setBackgroundRef(mapPresenter.getArea(2).getImgRef());
                    break;

            }

        }
    };
}