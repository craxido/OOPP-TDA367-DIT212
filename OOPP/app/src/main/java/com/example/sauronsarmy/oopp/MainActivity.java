package com.example.sauronsarmy.oopp;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sauronsarmy.oopp.clock.Runner;
import com.example.sauronsarmy.oopp.monsterPack.Monster;
import com.example.sauronsarmy.oopp.clock.ClockListener;


public class MainActivity extends AppCompatActivity implements MainMVPInterface.ViewOps,ClockListener {

    private MainMVPInterface.PresenterOps mainPresenter;
    private static final String TAG = "MainActivity";


    private Runner run = Runner.getInstance();
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);

        // Calling the constructor in onCreate since we need to send the context
        // and the activity must be created before sending it.
        mainPresenter = new MainPresenter(MainActivity.this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        ImageButton nxtLvl = (ImageButton) findViewById(R.id.nextLvl);
        ImageButton prvLvl = (ImageButton) findViewById(R.id.prevLvl);


        homeButton.setOnClickListener(buttonListener);
        shopButton.setOnClickListener(buttonListener);
        mapButton.setOnClickListener(buttonListener);
        statsButton.setOnClickListener(buttonListener);
        mainButton.setOnClickListener(buttonListener);
        monsterButton.setOnClickListener(buttonListener);

        nxtLvl.setOnClickListener(buttonListener);
        prvLvl.setOnClickListener(buttonListener);

        mainButton.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimary));


        Monster currentMonster = mainPresenter.getCurrentMonster();
        monsterButton.setImageResource(currentMonster.getImageRef());
        update();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause() called");
        //Unregister from clock
        //run.unregister(this);
        super.onPause();
    }

    @Override
    protected void onStart(){
        //Register to clock
        run.register(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop called");
        mainPresenter.saveState(MainActivity.this);
        run.unregister(this);
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
                    //mainPresenter.checkLevelUnlocked(mainPresenter.getPathGoal());

                    update();
                    break;
                case R.id.nextLvl:
                    mainPresenter.nextLevel();
                    update();

                    break;
                case R.id.prevLvl:
		            mainPresenter.previousLevel();
                    update();

                    break;

            }
        }
    };

    @Override
    public void update() {
        Monster currentMonster = mainPresenter.getCurrentMonster();

        RelativeLayout bg = (RelativeLayout) findViewById(R.id.b_mainActivity);
        bg.setBackgroundResource(mainPresenter.getBGRef());

        ImageButton monsterButton=(ImageButton) findViewById(R.id.b_monster);
        monsterButton.setImageResource(currentMonster.getImageRef());

        TextView hp = (TextView) findViewById(R.id.hp);
        hp.setText("Health: " + currentMonster.getHealth() + " /"+ currentMonster.getMaxhealth());

        TextView goal = (TextView) findViewById(R.id.goal);
        int goali = mainPresenter.getGoal();
        int path  = mainPresenter.getPathGoal();
        goal.setText("Goal: " + path +"/" +goali);

        TextView moneyIndicator = (TextView) findViewById(R.id.moneyIndicator);
        moneyIndicator.setText(String.valueOf(mainPresenter.getPlayerMoney()));

        // Update next arrow
        ImageButton nextButton = (ImageButton) findViewById(R.id.nextLvl);
        nextButton.setVisibility(View.GONE);
        //nextButton.setImageResource(mainPresenter.getNextArrowImage());
        // Update prev arrow
        ImageButton prevButton = (ImageButton) findViewById(R.id.prevLvl);
        prevButton.setVisibility(View.GONE);
        //prevButton.setImageResource(mainPresenter.getPrevArrowImage());

        mainPresenter.update();
    }
}
