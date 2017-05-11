package com.example.sauronsarmy.oopp.Map;

import com.example.sauronsarmy.oopp.MonsterPack.Monster;
import com.example.sauronsarmy.oopp.MonsterPack.monsterFactory;

/**
 * Author: Jonatan Källman
 */

public class Level {

    private Monster monster;
    private int healthMultiplier;
    private int goldMultiplier;
    private com.example.sauronsarmy.oopp.Map.areaType areaType;

    private int goal=10;
    private int pathToGoal=0;
    private boolean completed=false;
    boolean available = false;


    public Level(Monster monster, int healthMultiplier, int goldMultiplier, areaType area) {
        this.monster = monster;
        this.healthMultiplier = healthMultiplier;
        this.goldMultiplier = goldMultiplier;
        this.areaType = area;
    }

    public areaType getArea(){
        return this.areaType;
    }

    public void setArea(areaType areaType){
        this.areaType=areaType;
    }

    public int getHealthMultiplier() {
        return healthMultiplier;
    }

    public void setHealthMultiplier(int healthMultiplier) {
        this.healthMultiplier = healthMultiplier;
    }

    public int getGoldMultiplier() {
        return goldMultiplier;
    }

    public void setGoldMultiplier(int goldMultiplier) {
        this.goldMultiplier = goldMultiplier;
    }

    public Monster getCurrentMonster() {

        if(monster ==null){
            setNewMonster();

        }

        return monster;
    }

    public void setCurrentMonster(Monster currentMonster) {
        this.monster = currentMonster;
    }

    public int damageMonster(int damage){

        int ret =0;
        if(monster.damageMonster(damage)){
            ret=monster.getGold();
            pathToGoal++;
            if(pathToGoal>=goal){
                completed=true;
                pathToGoal=goal;
            }
            setNewMonster();

        }
        return ret;
    }

    public void setNewMonster(){

        monsterFactory monFac=new monsterFactory();

        setCurrentMonster(monFac.getMonster(getHealthMultiplier()*100, getGoldMultiplier()*100, getArea()));

    }

    public int getGoal(){
        return goal;

    }

    public int getPathToGoal(){
        return pathToGoal;

    }

    public boolean getComplete(){

        return completed;
    }


}

