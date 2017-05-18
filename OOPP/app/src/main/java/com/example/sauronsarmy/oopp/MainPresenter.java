package com.example.sauronsarmy.oopp;
import android.content.Context;
import android.util.Log;

import com.example.sauronsarmy.oopp.clock.ClockListener;
import com.example.sauronsarmy.oopp.clock.Runner;
import com.example.sauronsarmy.oopp.map.MapMVPInterface;
import com.example.sauronsarmy.oopp.monsterPack.Monster;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;
import com.example.sauronsarmy.oopp.map.MapPresenter;

/**
 * Created by Jonatan on 24/04/2017.
 * @author everyone
 */

 public class MainPresenter implements MainMVPInterface.PresenterOps {
    // View reference

    private PlayerModelInterface playerModel;
    private MainMVPInterface.ModelInterface mainModel;
    private MapMVPInterface.PresenterOps mapPresenter;
    private final static String TAG = "MainPresenter";


    public MainPresenter(Context context) {
        playerModel = PlayerModel.getInstance(context);
        mainModel = MainModel.getInstance();
        mapPresenter = new MapPresenter();
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
        playerModel.saveState(context);
    }

    /**
     * If there is a previous saved state, asks the MainModel for this state
     * and sends this state to the PlayerModel for loading.
     * @param context The context from which this method was called.
     */
    @Override
    public void loadState(Context context) {
            Log.i(TAG, "Loading previous save.");
            //playerModel.loadState(context);
    }

    @Override
    public void update() {
    //    applyGPS();
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

    @Override
    public void nextLevel() {
        mapPresenter.nextLevel();
    }

    @Override
    public void previousLevel() {
        mapPresenter.previousLevel();
    }

    @Override
    public int getPlayerMoney() {
        return playerModel.getMoney();
    }

    public boolean getLvlCmp(){
        return mapPresenter.getCurrentArea().getCurrentLevel().getComplete();
    }
    public boolean getAreaCmp(){
        return mapPresenter.getCurrentArea().getComplete();
    }
}
