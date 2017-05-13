package com.example.sauronsarmy.oopp.Map;

import com.example.sauronsarmy.oopp.MonsterPack.Monster;
import com.example.sauronsarmy.oopp.MonsterPack.monsterFactory;

/**
 * @author Jonatan Källman
 */

class Level {

    private Monster monster;
    private int healthMultiplier;
    private int goldMultiplier;
    private com.example.sauronsarmy.oopp.Map.areaType areaType;


    Level(Monster monster, int healthMultiplier, int goldMultiplier, areaType area) {
        this.monster = monster;
        this.healthMultiplier = healthMultiplier;
        this.goldMultiplier = goldMultiplier;
        this.areaType = area;
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

        int ret =0;
        if(monster.damageMonster(damage)){
            ret=monster.getGold();
            setNewMonster();

        }
        return ret;
    }

    void setNewMonster(){

        monsterFactory monFac=new monsterFactory();

        setCurrentMonster(monFac.getMonster(getHealthMultiplier()*100, getGoldMultiplier()*100, getArea()));

    }
}
