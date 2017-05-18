package com.example.sauronsarmy.oopp.Map;

import android.util.Log;

import com.example.sauronsarmy.oopp.MainPresenter;
import com.example.sauronsarmy.oopp.MonsterPack.Monster;

/**
 * @author Jonatan Källman
 */

public class MapPresenter implements MapMVPInterface.PresenterOps {

    private static final String TAG = "MainActivity";

    private static MapPresenter mapPresenterInstance = new MapPresenter();
    private static MapMVPInterface.ModelOps map;
    private MainPresenter mainPresenter;

    public MapPresenter() {
        mainPresenter = MainPresenter.getInstance();
        mapPresenterInstance = this;
        map = Map.getInstance();

    }

    public static void setBackgroundRef(int ref) {
        map.setBackgroundRef(ref);
    }

    public int getBackgroundRef() {
        return map.getCurrentArea().getImgRef();

    }

    public Area getArea(int index) {
        return map.getArea(index);
    }

    public Area getCurrentArea(){ return map.getCurrentArea();}

    public int getGoal(){return getCurrentArea().getCurrentLevel().getGoal();}
    public int getPathGoal(){return getCurrentArea().getCurrentLevel().getPathToGoal();}

    public void onError(String errorMsg){

        Log.i(TAG, "Error: " + errorMsg);
    }


    public int getPlayerMoney(){
        return map.getPlayerMoney();
    }

    public static MapPresenter getInstance() {
        return mapPresenterInstance;
    }

    public int damageMonster(int damage){
        int ret = map.getCurrentArea().getCurrentLevel().damageMonster(damage);
        if (ret >0){
            map.getCurrentArea().checkComplete();
        }
        return ret;

    }

    @Override
    public boolean tryChangeAreaLevel(int level, int area) {

      return map.tryChangeAreaLevel(level,area);
    }

    public Level getCurrentLevel() {
        return map.getCurrentLevel();
    }

    public void setNewMonster() {
        map.getCurrentArea().getCurrentLevel().setNewMonster();
    }


    @Override
    public Monster getCurrentMonster() {
        return map.getCurrentArea().getCurrentLevel().getCurrentMonster();
    }

    public Level createLevel(areaType areaType) {
        return map.createLevel(areaType);
    }

    public Area[] getAreas() {
        return map.getAreas();
    }

    public int getLevelGoldMultiplier(int areaIndex, int levelIndex){
        return map.getLevelGoldMultiplier(areaIndex, levelIndex);
    }

    public int getLevelHealthMultiplier(int areaIndex, int levelIndex) {
        return map.getLevelHealthMultiplier(areaIndex, levelIndex);
    }

    public int getLevelAmount(int areaIndex) {
        return map.getLevelAmount(areaIndex);
    }

    public int getAreaAmount() {
        return map.getAreaAmount();
    }

    public Area createArea(int areaIndex) {
        return map.createArea(areaIndex);
    }

    public areaType getAreaType(int areaIndex) {
        return map.getAreaType(areaIndex);
    }

    public int getAreaBgRef(int areaIndex) {
        return map.getAreaBgRef(areaIndex);
    }

    public boolean compareLevels(Level a, Level b){
        return a.equals(b);
    }

    public boolean compareAreas(Area a, Area b){
        return a.equals(b);

    }



    public void nextLevel(){
        map.nextLevel();
    }

    public void previousLevel(){
        map.previousLevel();
    }

}


