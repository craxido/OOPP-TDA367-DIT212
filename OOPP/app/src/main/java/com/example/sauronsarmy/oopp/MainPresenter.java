package com.example.sauronsarmy.oopp;
import android.content.Context;
import android.util.Log;

import com.example.sauronsarmy.oopp.map.MapMVPInterface;
import com.example.sauronsarmy.oopp.monsterPack.Monster;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;
import com.example.sauronsarmy.oopp.map.MapPresenter;
import com.example.sauronsarmy.oopp.clock.ClockListener;
import com.example.sauronsarmy.oopp.clock.Runner;

/**
 * Created by Jonatan on 24/04/2017.
 * @author everyone
 */

 public class MainPresenter implements MainMVPInterface.PresenterOps, ClockListener {
    // View reference

    private Runner run = Runner.getInstance();
    private PlayerModelInterface playerModel;
    private MainMVPInterface.ModelInterface mainModel;
    private MapMVPInterface.PresenterOps mapPresenter;
    private final static String TAG = "MainPresenter";


    public MainPresenter() {
        playerModel = PlayerModel.getInstance();
        mainModel = new MainModel();
        mapPresenter = new MapPresenter();
        run.register(this);
        run.start();
    }

    //Called from MainActivity when a monster is clicked
    public void monsterClicked(){
        int gold = mapPresenter.damageMonster(playerModel.getDamage());
        if (gold > 0) {
            playerModel.addMoney(gold);
        }
    }

    public Monster getCurrentMonster() {
        return mapPresenter.getCurrentMonster();
    }

    /**
     * Asks the PlayerModel for the current state, and sends this
     * to the MainModel for saving.
     * @param context The context from which this method was called.
     */
    @Override
    public void saveState(Context context) {
        Log.i(TAG, "Saving the current state.");
        java.util.Map currentState       = playerModel.getState();
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

    private void applyDPS(){
        int gold = mapPresenter.damageMonster(playerModel.getDamagePerSecond());
        if(gold > 0){
            playerModel.addMoney(gold);
        }
    }
    private void applyGPS(){
        playerModel.addMoney(playerModel.getMoneyPerSecond());
    }

    public Runner getRun(){return  run;}

    @Override
    public int getGoal() {
        return mapPresenter.getGoal();
    }

    @Override
    public int getPathGoal() {
       return mapPresenter.getPathGoal();
    }

    public int getBGRef(){
        return mapPresenter.getBackgroundRef();
    }

    public boolean getLvlCmp(){
        return mapPresenter.getCurrentArea().getCurrentLevel().getComplete();
    }
    public boolean getAreaCmp(){
        return mapPresenter.getCurrentArea().getComplete();
    }
}
