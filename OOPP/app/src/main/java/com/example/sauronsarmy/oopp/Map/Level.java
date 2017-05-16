package com.example.sauronsarmy.oopp.Map;

import com.example.sauronsarmy.oopp.MonsterPack.Monster;
import com.example.sauronsarmy.oopp.MonsterPack.monsterFactory;

/**
 * @author Jonatan Källman
 */

public class Level {

    private Monster monster;
    private int healthMultiplier;
    private int goldMultiplier;
    private areaType areaType;

    private int goal=10;
    private int pathToGoal=0;
    private boolean completed=false;
    boolean available = false;


    Level(Monster monster, int healthMultiplier, int goldMultiplier, areaType area) {
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

    Monster getCurrentMonster() {

        if(monster ==null){
            setNewMonster();

        }

        return monster;
    }

    void setCurrentMonster(Monster currentMonster) {
        this.monster = currentMonster;
    }

    int damageMonster(int damage){

        int ret =-1;
        //If the monster died, update pathToGoal, the return value and set a new monster
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

    void setNewMonster(){

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
