package com.example.sauronsarmy.oopp.map;

import android.content.Context;
import android.util.Log;

import com.example.sauronsarmy.oopp.areaType;
import com.example.sauronsarmy.oopp.monsterPack.IMonster;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;

/**
 * @author Jonatan Källman
 *  This presenter handles the communication within the map package.
 *  Most methods are explained in their class file from the object which they are called.
 */
public class MapPresenter implements MapMVPInterface.PresenterOps {

    private static final String TAG = "MapPresenter";
    private static PlayerModelInterface playerModel;
    private static MapMVPInterface.ModelOps map;

    /**
     * Constructor without context, mainly for testing.
     */
    public MapPresenter() {
        map = Map.getInstance();
        playerModel = PlayerModel.getInstance();
    }

    /**
     * Constructor with context sent from MainActivity.
     * @param context : Context from MainActivity.
     */
    public MapPresenter(Context context){
        map = Map.getInstance(context);
        playerModel = PlayerModel.getInstance(context);
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

    /**
     * Method that applies damage to a monster. Also checks if monster is dead.
     */
    public void damageMonster(){
        int gold = map.damageMonster(playerModel.getDamage());
        if (gold >0) {
            playerModel.addMoney(gold);
            playerModel.increaseMonsterKilled();
        }
    }

    /**
     * Method that applies damage per second. Also checks if monster is dead.
     */
    public void applyDPS() {
        int gold = map.damageMonster(playerModel.getDamagePerSecond());
        if (gold >0) {
            playerModel.addMoney(gold);
            playerModel.increaseMonsterKilled();
        }
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

    @Override
    public int getLevelIndex() {
        return map.getCurrentLevel().getLevelIndex();
    }

    @Override
    public int getAreaIndex() {
        return map.getCurrentArea().getAreaIndex();
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
