package com.example.sauronsarmy.oopp;
import android.content.Context;
import android.util.Log;

import com.example.sauronsarmy.oopp.Map.Map;
import com.example.sauronsarmy.oopp.MonsterPack.Monster;
import com.example.sauronsarmy.oopp.Player.PlayerModel;
import com.example.sauronsarmy.oopp.Player.PlayerModelInterface;
import com.example.sauronsarmy.oopp.clock.ClockListener;
import com.example.sauronsarmy.oopp.clock.Runner;

import java.lang.ref.WeakReference;

/**
 * Created by Jonatan on 24/04/2017.
 */

public class MainPresenter implements MainMVPInterface.PresenterOps,ClockListener {
    // View reference
    private static MainPresenter ourInstance;

    private Runner run = new Runner();

    Map map = Map.getInstance();


    WeakReference<MainMVPInterface.ViewOps> mView;
    PlayerModelInterface playerModel;
    MainMVPInterface.ModelInterface mainModel;
    private final static String TAG = "MainPresenter";


    public MainPresenter(MainMVPInterface.ViewOps mView) {
        this.mView = new WeakReference<>(mView);
        playerModel = PlayerModel.getInstance();
        mainModel = new MainModel();
        ourInstance =this;

        run.register(this);
        run.start();

    }

    public static MainPresenter getInstance(){

        return ourInstance;
    }
    // A configuration changed
    @Override
    public void onConfigChange(MainMVPInterface.ViewOps view) {
        mView = new WeakReference<>(view);
        map.getCurrentArea().getCurrentLevel().setNewMonster();
    }

    @Override
    public void onDestroy(boolean isChangingConfig){} //To be implemented
    @Override
    public void onError(String msg){} //To be implemented


    //Called from MainActivity when a monster is clicked
    public void monsterClicked(){

        int gold;
        if((gold =map.getCurrentArea().getCurrentLevel().damageMonster(playerModel.getDamage()) )!=0){

            playerModel.setMoney(playerModel.getMoney() +gold);

        }

    }




    public Monster getCurrentMonster() {


        return map.getCurrentArea().getCurrentLevel().getCurrentMonster();
    }
    /**
     * Asks the PlayerModel for the current state, and sends this
     * to the MainModel for saving.
     * @param context The context from which this method was called.
     */
    @Override
    //TODO When Map has its own package, simplify
    public void saveState(Context context) {
        Log.i(TAG, "Saving the current state.");
        java.util.Map currentState = playerModel.getState();
        mainModel.saveState(context, currentState);
    }

    /**
     * If there is a previous saved state, asks the MainModel for this state
     * and sends this state to the PlayerModel for loading.
     * @param context The context from which this method was called.
     */
    @Override
    public void loadState(Context context) {
        if(mainModel.hasSaveToLoad()) {
            Log.i(TAG, "Loading previous save.");
            playerModel.setState(mainModel.loadState(context));
        }

    }

    @Override
    public void update() {

        applyGPS();
        applyDPS();

    }

    public void applyDPS(){

        int gold;
        if((gold =map.getCurrentArea().getCurrentLevel().damageMonster(playerModel.getDamageMultiplier()) )!=0){

            playerModel.setMoney(playerModel.getMoney() +gold);
        }

    }

    public void applyGPS(){
        playerModel.setMoney(playerModel.getMoney() + (int)playerModel.getMoneyPerSecond());

    }

    public Runner getRun(){return  run;}


}
