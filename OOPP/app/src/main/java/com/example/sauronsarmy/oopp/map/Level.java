package com.example.sauronsarmy.oopp.map;

import com.example.sauronsarmy.oopp.areaType;
import com.example.sauronsarmy.oopp.monsterPack.IMonster;
import com.example.sauronsarmy.oopp.monsterPack.monsterFactory;

/**
 * @author Jonatan Källman
 */

public class Level {

    private IMonster monster;
    private int healthMultiplier;
    private int goldMultiplier;
    private com.example.sauronsarmy.oopp.areaType areaType;
    MapPresenter map = new MapPresenter();
    private int goal=10;
    private int pathToGoal=0;
    private boolean completed=false;

    Level(IMonster monster, int healthMultiplier, int goldMultiplier, areaType area) {
        this.monster = monster;
        this.healthMultiplier = healthMultiplier;
        this.goldMultiplier = goldMultiplier;
        this.areaType = area;
    }

    public boolean equals(Level other){
        return this.monster.equals(other.monster) && this.healthMultiplier == other.healthMultiplier
                && this.goldMultiplier == other.goldMultiplier && this.areaType == other.areaType;
    }

    areaType getArea(){
        return this.areaType;
    }

    void setArea(areaType areaType){
        this.areaType=areaType;
    }

    int getHealthMultiplier() {
        return healthMultiplier;
    }

    void setHealthMultiplier(int healthMultiplier) {
        this.healthMultiplier = healthMultiplier;
    }

    int getGoldMultiplier() {
        return goldMultiplier;
    }

    void setGoldMultiplier(int goldMultiplier) {
        this.goldMultiplier = goldMultiplier;
    }

    IMonster getCurrentMonster() {

        if(monster ==null){
            setNewMonster();

        }

        return monster;
    }

    void setCurrentMonster(IMonster currentMonster) {
        this.monster = currentMonster;
    }

    int damageMonster(int damage){

        int ret =-1;
        //If the monster died, update pathToGoal, the return value and set a new monster
        if(monster.damageMonster(damage)){

            ret=monster.getGold();
            pathToGoal++;
            map.addClearedGoal(1); //A goal is cleared, save it in map.
            if(pathToGoal>=goal){
                completed=true;
                pathToGoal=goal;
            }
            setNewMonster();

        }
        return ret;
    }

    void setNewMonster(){

        monsterFactory monFac=new monsterFactory();
        if (!(pathToGoal==9)){
            setCurrentMonster(monFac.getMonster(getHealthMultiplier(), getGoldMultiplier()*100, getArea()));
        }
        else { //It's time for a boss monster!
            setCurrentMonster(monFac.getBossMonster(getHealthMultiplier()*10, getGoldMultiplier()*100, getArea()));
        }
    }


    public int getGoal(){
        return goal;
    }

    public int getPathToGoal(){
        return pathToGoal;
    }

    public void setComplete(boolean bool){
        completed=bool;
    }

    public boolean getComplete(){
        return completed;
    }



}
