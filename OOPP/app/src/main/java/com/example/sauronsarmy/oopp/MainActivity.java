package com.example.sauronsarmy.oopp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sauronsarmy.oopp.clock.Runner;
import com.example.sauronsarmy.oopp.monsterPack.Monster;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;
import com.example.sauronsarmy.oopp.clock.ClockListener;


public class MainActivity extends AppCompatActivity implements MainMVPInterface.ViewOps,ClockListener {

    private Monster currentMonster;
    private MainMVPInterface.PresenterOps mainPresenter = new MainPresenter();
    private static final String TAG = "MainActivity";
    private PlayerModelInterface player;
    private Runner run = mainPresenter.getRun();
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = PlayerModel.getInstance();
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
        run.register(this);
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause() called");

        //Unregister from clock
        run.unregister(this);
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
        run.register(this);

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
            // Figure out which button was pressed
            switch (v.getId()) {
                case R.id.b_home:
                    intent.setAction("android.intent.action.HOME");
                    startActivity(intent);
                    break;
                case R.id.b_map:
                    intent.setAction("android.intent.action.MAP");
                    startActivity(intent);
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
                    break;
                case R.id.b_monster:

                    mainPresenter.monsterClicked();

                    update();
                    break;
            }
        }
    };

    @Override
    public void update() {
        currentMonster = mainPresenter.getCurrentMonster();

        RelativeLayout bg = (RelativeLayout) findViewById(R.id.b_mainActivity);
        bg.setBackgroundResource(mainPresenter.getBGRef());

        ImageButton monsterButton=(ImageButton) findViewById(R.id.b_monster);

        TextView hp = (TextView) findViewById(R.id.hp);
        hp.setText("Health: " + currentMonster.getHealth() + " /"+ currentMonster.getMaxhealth());

        TextView goal = (TextView) findViewById(R.id.goal);
        int goali = mainPresenter.getGoal();
        int path  = mainPresenter.getPathGoal();
        goal.setText("Goal: " + path +"/" +goali);

        monsterButton.setImageResource(currentMonster.getImageRef());
        TextView moneyIndicator = (TextView) findViewById(R.id.moneyIndicator);
        moneyIndicator.setText(String.valueOf(player.getMoney()));

    }
}
