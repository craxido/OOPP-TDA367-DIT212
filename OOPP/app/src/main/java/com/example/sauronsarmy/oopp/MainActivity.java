package com.example.sauronsarmy.oopp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sauronsarmy.oopp.Stats.StatsActivity;

public class MainActivity extends AppCompatActivity implements MainMVPInterface.ViewOps {
    int mon=0;
    int health=30;
    Monster currentMonster;
    monsterFactory monFac = new monsterFactory();
    private MainMVPInterface.PresenterOps mainPresenter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this);

        // Load previous state
        Log.i(TAG, "Will attempt to load previous state if there is one");
        mainPresenter.loadState(MainActivity.this);

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

        //currentMonster = main.getCurrentMonster;

        //Temp

        currentMonster = monFac.getMonster(30,30,areaType.FOREST);

        TextView hp = (TextView) findViewById(R.id.hp);
        hp.setText(currentMonster.getHealth() + " /"+ currentMonster.getMaxhealth());
        monsterButton.setImageResource(currentMonster.getImageRef());

    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause() called");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy() called");
        // Save current state
        Log.i(TAG, "Calling saveState() in mainPresenter");
        mainPresenter.saveState(MainActivity.this);
        super.onDestroy();
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
                    PlayerModelInterface p = PlayerModel.getInstance();

                    if(currentMonster.damageMonster(p.getDamage())){

                        p.setMoney(p.getMoney()+currentMonster.getGold());
                        if(mon==0) {
                            currentMonster=monFac.getMonster(currentMonster.getMaxhealth(),currentMonster.getGold(),areaType.MOUNTAIN);
                            mon++;
                        }
                        else {
                            currentMonster=monFac.getMonster(currentMonster.getMaxhealth(),currentMonster.getGold(),areaType.FOREST);
                            mon=0;
                        }
                    }

                    hp.setText(currentMonster.getHealth() + " /"+ currentMonster.getMaxhealth());
                    monsterButton.setImageResource(currentMonster.getImageRef());
                    break;
            }
        }
    };
}
