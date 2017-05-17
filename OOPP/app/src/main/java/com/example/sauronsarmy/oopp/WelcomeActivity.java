package com.example.sauronsarmy.oopp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/*
* Created by Filip Labe 2017-04-30
*
* */



public class WelcomeActivity extends AppCompatActivity implements MainMVPInterface.ViewOps{
    private static final String TAG ="WelcomeActivity";
    private SharedPreferences saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG,"onCreate() called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        Button loadGame = (Button) findViewById(R.id.loadGame);
        Button newGame = (Button) findViewById(R.id.newGame);

        loadGame.setOnClickListener(buttonListener);
        newGame.setOnClickListener(buttonListener);
    }


    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // Figure out which button was pressed

            switch (v.getId()){

                case R.id.newGame:
                    newGame();
                    break;
                case R.id.loadGame:
                    loadGame();
                    break;
            }
        }
    };


    // Load a previous game
    private void loadGame(){
        startActivity(new Intent().setAction("android.intent.action.MAINSCREEN"));
    }
    // Create a new game
    private void newGame(){
        final Context context = WelcomeActivity.this;

        //Create a dialog, asking if the player wants to override any previous save
        AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
        builder.setMessage(R.string.new_game_prompt)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Clear all saved data
                        saveData = context.getSharedPreferences(context.getString(R.string.stateIdentifier), Context.MODE_PRIVATE);
                        saveData.edit().clear().apply();
                        //Chose new game
                        startActivity(new Intent().setAction("android.intent.action.MAINSCREEN"));
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        AlertDialog dia = builder.create();
        dia.show();
    }
}
