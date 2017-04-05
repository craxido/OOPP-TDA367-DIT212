package com.example.sauronsarmy.oopp;

/**
 * Created by Jonatan on 2017-04-05.
 */


class Level {
    private Monster[] monsters;
    private double healthMultiplier;
    private double goldMultiplier;
    private areaType areaType;

    public Level(Monster[] monsters, double healthMultiplier, double goldMultiplier, areaType area) {
        this.monsters = monsters;
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

    public double getGoldMultiplier() {
        return goldMultiplier;
    }

    public void setGoldMultiplier(double goldMultiplier) {
        this.goldMultiplier = goldMultiplier;
    }


}
