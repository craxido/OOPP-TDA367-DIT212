package com.example.sauronsarmy.oopp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sauronsarmy.oopp.clock.Runner;
import com.example.sauronsarmy.oopp.monsterPack.BossMonster;
import com.example.sauronsarmy.oopp.monsterPack.IMonster;
import com.example.sauronsarmy.oopp.clock.ClockListener;



public class MainActivity extends AppCompatActivity implements MainMVPInterface.ViewOps,ClockListener {

    private MainMVPInterface.PresenterOps mainPresenter;
    private final String TAG = "MainActivity";


    private Runner run = Runner.getInstance();
    private Intent intent = new Intent();
    private ImageButton monsterButton;
    private ImageView bossFight;
    private ProgressBar bossTimer;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private RelativeLayout bg;
    private TextView hp;
    private TextView goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);

        // Calling the constructor in onCreate since we need to send the context
        // and the activity must be created before sending it.

        mainPresenter = new MainPresenter(this);
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
        monsterButton = (ImageButton) findViewById(R.id.b_monster);
        bossFight = (ImageView) findViewById(R.id.bossFightText);
        bossTimer = (ProgressBar) findViewById(R.id.bossTimer);
        bg  = (RelativeLayout) findViewById(R.id.b_mainActivity);
        hp = (TextView) findViewById(R.id.hp);
        goal = (TextView) findViewById(R.id.goal);

        nextButton = (ImageButton) findViewById(R.id.nextLvl);
        prevButton = (ImageButton) findViewById(R.id.prevLvl);

        nextButton.setTag(0);
        prevButton.setTag(0);


        homeButton.setOnClickListener(buttonListener);
        shopButton.setOnClickListener(buttonListener);
        mapButton.setOnClickListener(buttonListener);
        statsButton.setOnClickListener(buttonListener);
        mainButton.setOnClickListener(buttonListener);
        monsterButton.setOnClickListener(buttonListener);

        nextButton.setOnClickListener(buttonListener);
        prevButton.setOnClickListener(buttonListener);

        mainButton.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimary));


        IMonster currentMonster = mainPresenter.getCurrentMonster();
        monsterButton.setImageResource(currentMonster.getImageRef());
        monsterButton.setTag(currentMonster.getImageRef());

        if(currentMonster.isBoss()){
            // show the text and progress bar.
            bossFight.setVisibility(View.VISIBLE);
            bossTimer.getProgressDrawable().setColorFilter(0xFFFF0000, PorterDuff.Mode.SRC_ATOP);
            bossTimer.setVisibility(View.VISIBLE);
            int max = ((BossMonster) currentMonster).getTimeLimit();
            bossTimer.setMax(max);
        } else {
            // hide the boss text and progress bar
            (findViewById(R.id.bossFightText)).setVisibility(View.INVISIBLE);
            (findViewById(R.id.bossTimer)).setVisibility(View.INVISIBLE);
        }

        update();
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
                    finish();
                    break;
                case R.id.b_map:
                    intent.setAction("android.intent.action.MAP");
                    startActivity(intent);
                    finish();
                    break;
                case R.id.b_shop:
                    intent.setAction("android.intent.action.SHOP");
                    startActivity(intent);
                    finish();
                    break;
                case R.id.b_stats:
                    intent.setAction("android.intent.action.STATS");
                    startActivity(intent);
                    finish();
                    break;
                case R.id.b_main:
                    break;
                case R.id.b_monster:
                    mainPresenter.monsterClicked();
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
        IMonster currentMonster = mainPresenter.getCurrentMonster();

        bg.setBackgroundResource(mainPresenter.getBGRef());

        int imageRef = (Integer) monsterButton.getTag();
        if (imageRef != currentMonster.getImageRef()){
            monsterButton.setImageResource(currentMonster.getImageRef());
            monsterButton.setTag(currentMonster.getImageRef());
            if(currentMonster.isBoss()){
                // show the text and progress bar.
                bossFight.setVisibility(View.VISIBLE);
                bossTimer.getProgressDrawable().setColorFilter(0xFFFF0000, PorterDuff.Mode.SRC_ATOP);
                bossTimer.setVisibility(View.VISIBLE);
                int max = ((BossMonster) currentMonster).getTimeLimit();
                bossTimer.setMax(max);
            } else {
                // hide the boss text and progress bar
                (findViewById(R.id.bossFightText)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.bossTimer)).setVisibility(View.INVISIBLE);
            }
        }

        if(currentMonster.isBoss()){
            // if boss then update the progress bar
            int progress = ((BossMonster) currentMonster).getTime();
            bossTimer.setProgress(progress);
        }

        hp.setText("Health: " + currentMonster.getHealth() + " /"+ currentMonster.getMaxHealth());

        int goali = mainPresenter.getGoal();
        int path  = mainPresenter.getPathGoal();
        goal.setText("Goal: " + path +"/" +goali);

        TextView moneyIndicator = (TextView) findViewById(R.id.moneyIndicator);
        moneyIndicator.setText(String.valueOf(mainPresenter.getPlayerMoney()));

        // Update next arrow
        int nextArrow = (Integer) mainPresenter.getNextArrowImage();
        //See if the image has changed
        if((Integer) nextButton.getTag()!=nextArrow) {
            nextButton.setImageResource(nextArrow);
            nextButton.setTag(nextArrow);
        }
        // Update prev arrow
        int prevArrow = (Integer)mainPresenter.getPrevArrowImage();
        //See if the image has changed
        if((Integer) prevButton.getTag() != prevArrow){
            prevButton.setImageResource(prevArrow);
            prevButton.setTag(prevArrow);
        }

        mainPresenter.update();
    }
}
