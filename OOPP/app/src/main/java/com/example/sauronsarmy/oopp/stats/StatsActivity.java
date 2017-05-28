package com.example.sauronsarmy.oopp.stats;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sauronsarmy.oopp.clock.Runner;
import com.example.sauronsarmy.oopp.R;
import com.example.sauronsarmy.oopp.clock.ClockListener;

public class StatsActivity extends AppCompatActivity implements ClockListener {

    private StatsInterface.Presenter statsPresenter = new StatsPresenter();
    private Runner run = Runner.getInstance();
    private Intent intent = new Intent();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

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
        statsButton.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimary));

        update();

        run.register(this);
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
                    break;
                case R.id.b_main:
                    intent.setAction("android.intent.action.MAINSCREEN");
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };


    @Override
    protected void onStop(){
        //Register to clock
        statsPresenter.saveState(StatsActivity.this);
        run.unregister(this);
        super.onStop();
    }

    @Override
    protected void onStart(){
        //Register to clock
        run.register(this);
        super.onStart();
    }


    //Update the fields with current values
    @Override
    public void update() {
           /*
             Setting all the textViews to display the correct stats.
            */
        TextView damageText = (TextView) findViewById(R.id.damageText);
        TextView dpsText = (TextView) findViewById(R.id.dpsText);
        TextView moneyText = (TextView) findViewById(R.id.moneyText);
        TextView moneyPerSecText = (TextView) findViewById(R.id.moneyperSecText);
        TextView monstersKilled = (TextView) findViewById(R.id.monstersKilled);

        damageText.setText(String.valueOf(statsPresenter.getPlayerDamage()));
        dpsText.setText(String.valueOf(statsPresenter.getPlayerDamagePerSecond()));
        moneyText.setText(String.valueOf(statsPresenter.getMoneyAmount()));
        moneyPerSecText.setText(String.valueOf(statsPresenter.getMoneyPerSecond()));
        monstersKilled.setText(String.valueOf(statsPresenter.getMonstersKilled()));

        statsPresenter.update();
    }
}
