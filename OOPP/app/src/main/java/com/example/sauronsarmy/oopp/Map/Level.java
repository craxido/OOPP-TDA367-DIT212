package com.example.sauronsarmy.oopp.Map;

import com.example.sauronsarmy.oopp.Monster;

/**
 * Author: Jonatan Källman
 */

public class Level {

    private Monster monster;
    private double healthMultiplier;
    private int goldMultiplier;
    private com.example.sauronsarmy.oopp.Map.areaType areaType;


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
}
