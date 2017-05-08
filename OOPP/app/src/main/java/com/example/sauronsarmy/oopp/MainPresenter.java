package com.example.sauronsarmy.oopp;
import android.content.Context;
import android.util.Log;

import com.example.sauronsarmy.oopp.Map.Map;
import com.example.sauronsarmy.oopp.Player.PlayerModel;
import com.example.sauronsarmy.oopp.Player.PlayerModelInterface;

import java.lang.ref.WeakReference;

/**
 * Created by Jonatan on 24/04/2017.
 */

class MainPresenter implements MainMVPInterface.PresenterOps {
    // View reference

    private static MainPresenter ourInstance;



    Shop shop = Shop.getInstance();
    Map map = Map.getInstance();


    monsterFactory monFac=new monsterFactory();


    WeakReference<MainMVPInterface.ViewOps> mView;
    PlayerModelInterface playerModel;
    MainMVPInterface.ModelInterface mainModel;
    private final static String TAG = "MainPresenter";


    public MainPresenter(MainMVPInterface.ViewOps mView) {
        this.mView = new WeakReference<>(mView);
        playerModel = PlayerModel.getInstance();
        mainModel = new MainModel();
        ourInstance =this;

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
        java.util.Map currentUpgrade = null; // Shop.getUpgrade ....
        mainModel.saveState(context, currentState, currentUpgrade);
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
            // Shop.setUpgrade(mainModel.loadUpgrade(context));
        }

    }
}
