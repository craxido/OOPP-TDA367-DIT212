package com.example.sauronsarmy.oopp.map;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauronsarmy.oopp.clock.Runner;
import com.example.sauronsarmy.oopp.upgrades.HomeActivity;
import com.example.sauronsarmy.oopp.MainActivity;
import com.example.sauronsarmy.oopp.R;
import com.example.sauronsarmy.oopp.upgrades.ShopActivity;
import com.example.sauronsarmy.oopp.stats.StatsActivity;
import com.example.sauronsarmy.oopp.clock.ClockListener;
import com.example.sauronsarmy.oopp.lvlPickFragment;

public class MapActivity extends AppCompatActivity
        implements MapMVPInterface.ViewOps,lvlPickFragment.ClickListener, ClockListener {


    private MapMVPInterface.PresenterOps mapPresenter;
    private static final Runner run = Runner.getInstance();
    private static final String TAG = "MapActivity";
    private Intent intent = new Intent();

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

        /* Get the map (there is only one map). */
        mapPresenter = new MapPresenter();
        run.register(this);
        TextView moneyIndi = (TextView) findViewById(R.id.moneyIndicator);
        moneyIndi.setText(String.valueOf(mapPresenter.getPlayerMoney()));

    }

    @Override
    public void update(){
        TextView moneyIndi = (TextView) findViewById(R.id.moneyIndicator);
        moneyIndi.setText(String.valueOf(mapPresenter.getPlayerMoney()));
    }

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Context context = MapActivity.this;
            // Figure out which button was pressed
            switch (v.getId()) {
                case R.id.b_home:
                    intent.setAction("android.intent.action.HOME");
                    startActivity(intent);
                    break;
                case R.id.b_map:
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
                /* TODO: Selecting a new area should result in changing the monsters/ levels
                 * TODO: as well as the background in the MainActivity.
                    */
                case R.id.b_area1:

                    showDia(0);
                    break;
                case R.id.b_area2:

                    showDia(1);
                    break;
                case R.id.b_area3:

                    showDia(2);
                    break;

            }

        }
    };

    public void showDia(int area){
        //Create a new fragment
        lvlPickFragment lvlpck = new lvlPickFragment();
        //Pass the area as a argument
        Bundle args = new Bundle();
        args.putInt("area",area);
        lvlpck.setArguments(args);

        //Show the fragment
        lvlpck.show(getSupportFragmentManager(),"Lvlpick fragment");

    }

    @Override
    public void onClick(int level, int area) {
        //Log the selected level and area
        Log.i("LvlArea",level + " "+ area);
        if(mapPresenter.tryChangeAreaLevel(level,area)) {

            startActivity(new Intent(this, MainActivity.class));
        }
        else {

            Toast toast = Toast.makeText(this,"You have not unlocked this level yet",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
