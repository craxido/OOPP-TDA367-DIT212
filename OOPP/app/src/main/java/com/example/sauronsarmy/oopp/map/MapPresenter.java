package com.example.sauronsarmy.oopp.map;

import android.content.Context;
import android.util.Log;

import com.example.sauronsarmy.oopp.monsterPack.Monster;
import com.example.sauronsarmy.oopp.player.PlayerModel;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;

/**
 * @author Jonatan Källman
 */

public class MapPresenter implements MapMVPInterface.PresenterOps {

    private static final String TAG = "MainActivity";

    private static PlayerModelInterface playerModel;
    private static MapMVPInterface.ModelOps map;

    public MapPresenter() {
        map = Map.getInstance();
        playerModel = PlayerModel.getInstance();
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
    }

    public int getPlayerMoney(){
        return playerModel.getMoney();
    }

    public void changeArea(int index){
        map.setCurrentArea(map.getArea(index));
        int imgref= map.getArea(index).getImgRef();
        setBackgroundRef(imgref);
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

        if(area ==0){
            changeArea(area);
            if(level ==0){
                changeLvl(level);
                return true;
            }
            else {

                if(getCurrentArea().getLevel(level-1)!=null && (getCurrentArea().getLevel(level-1).getComplete())){
                    changeLvl(level);
                    return true;
                }

            }
        }
        else {

            if(getArea(area-1).getComplete() && map.getAreas().length>area){
                changeArea(area);
                if(level ==0){
                    changeLvl(level);
                    return true;
                }
                else {

                    if(getCurrentArea().getLevel(level-1)!=null && (getCurrentArea().getLevel(level-1).getComplete())){
                        changeLvl(level);
                        return true;
                    }
                }
            }
        }
        return false;
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

        int lvlpos = getLevelIndex() +1;
        if(lvlpos >= getCurrentArea().getLevels().length){
            if(getAreaIndex()+1 < getAreas().length){

                tryChangeAreaLevel(0,getAreaIndex()+1);
            }

        }
        else{
            tryChangeAreaLevel(lvlpos,getAreaIndex());
        }
    }

    public void previousLevel(){
        int lvlpos = getLevelIndex() -1;
        if(lvlpos <0){
            if(getAreaIndex()-1 >=0){

                tryChangeAreaLevel(getArea(getAreaIndex()-1).getLevels().length -1 ,getAreaIndex()-1);
            }

        }
        else{
            tryChangeAreaLevel(lvlpos,getAreaIndex());
        }

    }

    private int getLevelIndex(){

        int pos =0;
        Level curLvl = getCurrentLevel();
        Level[] lvls = getCurrentArea().getLevels();

        for(int i =0; i< lvls.length ; i++){
            if(lvls[i].equals(curLvl)){
                pos =i;
                break;

            }
        }
        return pos;
    }


    private int getAreaIndex(){

        int pos =0;
        Area currentArea = getCurrentArea();
        Area[] areas = getAreas();

        for(int i =0; i< areas.length ; i++){
            if(areas[i].equals(currentArea)){
                pos =i;
                break;

            }
        }
        return pos;
    }

}
