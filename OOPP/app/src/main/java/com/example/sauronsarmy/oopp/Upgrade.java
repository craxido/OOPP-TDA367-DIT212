package com.example.sauronsarmy.oopp;

/**
 * Created by bunnyfiscuit on 05/04/17.
 */

class Upgrade {

    private double baseStat;
    private double currentStat;
    private double baseCost;
    private double currentCost;

    protected Upgrade(double baseStat, double baseCost){
        this.baseStat = this.currentStat = baseStat;
        this.baseCost = this.currentCost = baseCost;

    }

    // Updates current stat upgrade amount with a multiplier
    protected void updateStat(int multiplier){
        currentStat *= multiplier;
    }

    // Updates the cost for the upgrade with multiplier
    protected void updateCost(int multiplier){
        if(multiplier > 1) {
            currentCost *= (multiplier / 2);
        }
    }

    // if one needs to get the base stats
    protected double getBaseStat(){
        return this.baseStat;
    }
    // mostly what will be used
    protected double getCurrentStat(){
        return this.currentStat;
    }

    protected double getBaseCost(){
        return this.baseCost;
    }

    protected double getCurrentCost(){
        return this.currentCost;
    }

}
