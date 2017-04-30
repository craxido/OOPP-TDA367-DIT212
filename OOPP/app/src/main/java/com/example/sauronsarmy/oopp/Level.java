package com.example.sauronsarmy.oopp;

/**
 * Created by Jonatan on 2017-04-05.
 */


class Level {

    private Monster monster;
    private double healthMultiplier;
    private int goldMultiplier;
    private areaType areaType;


    Level(Monster monster, double healthMultiplier, int goldMultiplier, areaType area) {
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

    public double getHealthMultiplier() {
        return healthMultiplier;
    }

    public void setHealthMultiplier(double healthMultiplier) {
        this.healthMultiplier = healthMultiplier;
    }

    public int getGoldMultiplier() {
        return goldMultiplier;
    }

    public void setGoldMultiplier(int goldMultiplier) {
        this.goldMultiplier = goldMultiplier;
    }

    public Monster getCurrentMonster() {
        return monster;
    }

    public void setCurrentMonster(Monster currentMonster) {
        this.monster = currentMonster;
    }

    public int damageMonster(double damage){

        int ret =0;
        if(monster.damageMonster(damage)){
            ret=monster.getGold();
            setNewMonster();

        }
        return ret;
    }

    public void setNewMonster(){

        monsterFactory monFac=new monsterFactory();

        setCurrentMonster(monFac.getMonster(getHealthMultiplier()*100, getGoldMultiplier()*100, getArea()));

    }


}

