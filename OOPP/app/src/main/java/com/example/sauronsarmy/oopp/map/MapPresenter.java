package com.example.sauronsarmy.oopp.map;

import android.content.Context;
import android.util.Log;

import com.example.sauronsarmy.oopp.areaType;
import com.example.sauronsarmy.oopp.monsterPack.IMonster;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;

/**
 * @author Jonatan Källman
 */

public class MapPresenter implements MapMVPInterface.PresenterOps {

    private static final String TAG = "MapPresenter";

    private static PlayerModelInterface playerModel;
    private static MapMVPInterface.ModelOps map;

    //Overloading
    public MapPresenter() {
        map = Map.getInstance();
        playerModel = PlayerModel.getInstance();
    }

    public MapPresenter(Context context){
        map = Map.getInstance(context.getApplicationContext());
        playerModel = PlayerModel.getInstance(context.getApplicationContext());
    }

    private static void setBackgroundRef(int ref) {
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

    @Override
    public void saveState(Context context){
        playerModel.saveState(context);
        map.saveState(context);
    }

    public int getPlayerMoney(){
        return playerModel.getMoney();
    }

    public void changeArea(int index){
        map.setCurrentArea(map.getArea(index));
        int imgref= map.getArea(index).getImgRef();
        setBackgroundRef(imgref);
    }

    public void damageMonster(){
        playerModel.addMoney(map.damageMonster(playerModel.getDamage()));
    }

    public void applyDPS() {
        playerModel.addMoney(map.damageMonster(playerModel.getDamagePerSecond()));
    }

    @Override
    public boolean tryChangeAreaLevel(int level, int area) {
        return map.tryChangeAreaLevel(level,area);
    }

    public void changeLvl(int index) {
        getCurrentArea().setCurrentLevel(getCurrentArea().getLevels()[index]);
    }

    public Level getCurrentLevel() {
        return map.getCurrentLevel();
    }

    public void setNewMonster() {
        map.getCurrentArea().getCurrentLevel().setNewMonster();
    }

    public IMonster getCurrentMonster() {
        return map.getCurrentArea().getCurrentLevel().getCurrentMonster();
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

    public boolean nextLevel(){
        return map.nextLevel();
    }

    public boolean previousLevel(){
        return map.previousLevel();
    }

    public int getMapBgRef(){
        return map.getMapBgRef();
    }

    public Level createMountainLvl(int levelIndex){
        return map.createMountainLvl(levelIndex);
    }

    public Level createForestLvl(int levelIndex){
        return map.createForestLvl(levelIndex);
    }

    public Level createVolcanoLvl(int levelIndex){
        return map.createVolcanoLvl(levelIndex);
    }

}
