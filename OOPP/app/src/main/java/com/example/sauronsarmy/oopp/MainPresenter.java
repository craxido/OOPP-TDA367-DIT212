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
                setNewMonster();
    }

    @Override
    public void onDestroy(boolean isChangingConfig){} //To be implemented
    @Override
    public void onError(String msg){} //To be implemented



    public void monsterClicked(){
        Monster currentMonster = map.getCurrentArea().getCurrentLevel().getCurrentMonster();
        boolean temp = currentMonster.damageMonster(playerModel.getDamage());
        if(temp){

            playerModel.setMoney(playerModel.getMoney()+currentMonster.getGold());
            setNewMonster();
        }
    }

    private void setNewMonster(){

        map.getCurrentArea().getCurrentLevel().setCurrentMonster(monFac.getMonster(
                map.getCurrentArea().getCurrentLevel().getHealthMultiplier()*100,
                map.getCurrentArea().getCurrentLevel().getGoldMultiplier()*100,
                map.getCurrentArea().getCurrentLevel().getArea()));

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
}
