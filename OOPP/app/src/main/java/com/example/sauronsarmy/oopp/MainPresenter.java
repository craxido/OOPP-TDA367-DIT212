package com.example.sauronsarmy.oopp;
import android.content.Context;
import android.util.Log;

import com.example.sauronsarmy.oopp.map.MapMVPInterface;
import com.example.sauronsarmy.oopp.monsterPack.IMonster;
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
        playerModel = PlayerModel.getInstance(context.getApplicationContext());
        mainModel = MainModel.getInstance();
        mapPresenter = new MapPresenter();
    }

    //Called from MainActivity when a monster is clicked
    public void monsterClicked(){
        mapPresenter.damageMonster();
    }

    public IMonster getCurrentMonster() {
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
        mapPresenter.saveState(context); //Saves player state and map state.
    }

    @Override
    public void update() {
        mapPresenter.applyDPS();
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
    public boolean nextLevel() {
        return mapPresenter.nextLevel();
    }

    @Override
    public void checkLevelUnlocked(int pathGoal){
        mainModel.checkLevelUnlocked(pathGoal);
    }

    @Override
    public void incrementCurrentLevel() {
        mainModel.incrementCurrentLevel();
    }

    @Override
    public void decrementCurrentLevel() {
        mainModel.decrementCurrentLevel();
    }

    @Override
    public int getNextArrowImage(){

         if(getLvlCmp() && !(mapPresenter.getArea(mapPresenter.getAreas().length-1).equals(mapPresenter.getCurrentArea())
                 && mapPresenter.getCurrentArea().getLevel(mapPresenter.getCurrentArea().getLevels().length-1).equals(mapPresenter.getCurrentArea().getCurrentLevel()))){
            return R.drawable.green_arrow_right;
        }
        else {
            return R.drawable.red_arrow_right;
        }
    }

    @Override
    public int getPrevArrowImage(){

        if(mapPresenter.getAreas()[0].equals(mapPresenter.getCurrentArea()) && mapPresenter.getCurrentArea().getLevels()[0].equals(mapPresenter.getCurrentArea().getCurrentLevel())){
            return R.drawable.red_arrow_left;
        }
        else {
            return R.drawable.green_arrow_left;
        }
    }

    @Override
    public boolean previousLevel() {
        return mapPresenter.previousLevel();
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
