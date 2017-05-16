package com.example.sauronsarmy.oopp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sauronsarmy.oopp.map.MapActivity;
import com.example.sauronsarmy.oopp.monsterPack.Monster;
import com.example.sauronsarmy.oopp.stats.StatsActivity;
import com.example.sauronsarmy.oopp.upgrades.HomeActivity;
import com.example.sauronsarmy.oopp.upgrades.ShopActivity;
import com.example.sauronsarmy.oopp.clock.ClockListener;


public class MainActivity extends AppCompatActivity implements MainMVPInterface.ViewOps,ClockListener {

    private Monster currentMonster;
    private MainMVPInterface.PresenterOps mainPresenter = new MainPresenter();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Clicking on Home/Shop/Map/Stats should send the user to the
        appropriate activity.
         */
        ImageButton homeButton  = (ImageButton) findViewById(R.id.b_home);
        ImageButton mapButton   = (ImageButton) findViewById(R.id.b_map);
        ImageButton statsButton = (ImageButton) findViewById(R.id.b_stats);
        ImageButton shopButton  = (ImageButton) findViewById(R.id.b_shop);
        ImageButton mainButton  = (ImageButton) findViewById(R.id.b_main);
        ImageButton monsterButton=(ImageButton) findViewById(R.id.b_monster);


        homeButton.setOnClickListener(buttonListener);
        shopButton.setOnClickListener(buttonListener);
        mapButton.setOnClickListener(buttonListener);
        statsButton.setOnClickListener(buttonListener);
        mainButton.setOnClickListener(buttonListener);
        monsterButton.setOnClickListener(buttonListener);

        mainButton.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimary));


        update();
        mainPresenter.getRun().register(this);
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause() called");

        //Unregister from clock
        mainPresenter.getRun().unregister(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume() called");
        super.onResume();
    }

    @Override
    protected void onStart(){

        //Register to clock
        mainPresenter.getRun().register(this);

        super.onStart();
    }
    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy() called");
        mainPresenter.saveState(MainActivity.this);
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop called");
        Log.i(TAG, "Calling saveState() in mainPresenter");
        mainPresenter.saveState(MainActivity.this);
        super.onStop();
    }
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
                case R.id.b_main:
                    break;
                case R.id.b_monster:
                    ImageButton monsterButton=(ImageButton) findViewById(R.id.b_monster);
                    TextView hp = (TextView) findViewById(R.id.hp);


                    mainPresenter.monsterClicked();

                    currentMonster = mainPresenter.getCurrentMonster();

                    hp.setText(currentMonster.getHealth() + " /"+ currentMonster.getMaxhealth());
                    monsterButton.setImageResource(currentMonster.getImageRef());

                    break;
            }
        }
    };

    @Override
    public void update() {
        currentMonster = mainPresenter.getCurrentMonster();
        ImageButton monsterButton=(ImageButton) findViewById(R.id.b_monster);
        TextView hp = (TextView) findViewById(R.id.hp);
        hp.setText(currentMonster.getHealth() + " /"+ currentMonster.getMaxhealth());
        monsterButton.setImageResource(currentMonster.getImageRef());
    }
}
