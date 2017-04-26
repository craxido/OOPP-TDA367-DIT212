package com.example.sauronsarmy.oopp;

/**
 * Created by Jonatan on 2017-04-05.
 */


class Level {

    private Monster monster;
    private double healthMultiplier;
    private int goldMultiplier;
    private areaType areaType;


    Level(Monster monster, double healthMultiplier, double goldMultiplier, areaType area) {
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
        return currentMonster;
    }

    public void setCurrentMonster(Monster currentMonster) {
        this.currentMonster = currentMonster;
    }
}
