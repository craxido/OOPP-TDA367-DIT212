package com.example.sauronsarmy.oopp;
import android.content.Context;
import android.util.Log;

import com.example.sauronsarmy.oopp.map.Map;
import com.example.sauronsarmy.oopp.monsterPack.Monster;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;
import com.example.sauronsarmy.oopp.upgrades.HomeMVPInterface;
import com.example.sauronsarmy.oopp.upgrades.HomePresenter;
import com.example.sauronsarmy.oopp.upgrades.ShopMVPInterface;
import com.example.sauronsarmy.oopp.upgrades.ShopPresenter;
import com.example.sauronsarmy.oopp.clock.ClockListener;
import com.example.sauronsarmy.oopp.clock.Runner;

import java.lang.ref.WeakReference;

/**
 * Created by Jonatan on 24/04/2017.
 * @author everyone
 */

public class MainPresenter implements MainMVPInterface.PresenterOps,ClockListener {
    // View reference
    private static MainPresenter ourInstance;
    private Runner run = new Runner();
    private Map map = Map.getInstance();
    private PlayerModelInterface playerModel;
    private ShopMVPInterface.Presenter shopPresenter;
    private HomeMVPInterface.Presenter homePresenter;
    private MainMVPInterface.ModelInterface mainModel;
    private final static String TAG = "MainPresenter";


    private MainPresenter() {
        playerModel = PlayerModel.getInstance();
        shopPresenter = new ShopPresenter();
        homePresenter = new HomePresenter();
        mainModel = new MainModel();
        ourInstance =this;

        run.register(this);
        run.start();

    }

    public static MainPresenter getInstance(){
        return ourInstance;
    }

    //Called from MainActivity when a monster is clicked
    public void monsterClicked(){
        int gold = map.getCurrentArea().getCurrentLevel().damageMonster(playerModel.getDamage());
        if (gold != 0) {
            playerModel.addMoney(gold);
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
    public void saveState(Context context) {
        Log.i(TAG, "Saving the current state.");
        java.util.Map currentState       = playerModel.getState();
        java.util.Map currentShopUpgrade = shopPresenter.getUpgradeCounters();
        java.util.Map currentHomeUpgrade = homePresenter.getUpgradeCounters();
        mainModel.saveState(context, currentState, currentShopUpgrade, currentHomeUpgrade);
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
            shopPresenter.setUpgradeCounters(mainModel.loadShopUpgrade(context));
            homePresenter.setOilPumpUpgradeCounter(mainModel.loadHomeUpgrade(context));
        }
    }

    @Override
    public void update() {
        applyGPS();
        applyDPS();
    }

    private void applyDPS(){
        int gold = map.getCurrentArea().getCurrentLevel().damageMonster(playerModel.getDamagePerSecond());
        if(gold != 0){
            playerModel.addMoney(gold);
        }

    }

    private void applyGPS(){
        playerModel.addMoney(playerModel.getMoneyPerSecond());
    }

    public Runner getRun(){return  run;}


}
