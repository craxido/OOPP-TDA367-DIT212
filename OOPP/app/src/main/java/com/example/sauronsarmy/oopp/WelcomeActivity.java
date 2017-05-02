package com.example.sauronsarmy.oopp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
    private MainMVPInterface.PresenterOps mainPresenter;



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
        mainPresenter = new MainPresenter(this);

        // Load previous state
        Log.i(TAG, "Will attempt to load previous state if there is one");
        mainPresenter.loadState(WelcomeActivity.this);

        Context context = WelcomeActivity.this;
        startActivity(new Intent(context, MainActivity.class));


    }
    // Create a new game
    private void newGame(){
        mainPresenter = new MainPresenter(this);

        //Creata a dialog, asking if the player wants to override any previous save
        AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
        builder.setMessage(R.string.new_game)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Choose new game
                        Context context = WelcomeActivity.this;
                        startActivity(new Intent(context, MainActivity.class));


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