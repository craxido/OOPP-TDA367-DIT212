package com.example.sauronsarmy.oopp;

/**
 * Created by bunnyfiscuit on 05/04/17.
 */

class Upgrade {

    private double stat;
    private int cost;

    protected Upgrade(double baseStat, int baseCost){
        this.stat = baseStat;
        this.cost =baseCost;

    }

    // Updates current stat upgrade amount with a multiplier
    protected void updateStat(int multiplier){
        stat *= multiplier;
    }

    // Updates the cost for the upgrade with multiplier
    protected void updateCost(int multiplier){
        if(multiplier > 1) {
            cost *= Math.round(multiplier / 2);
        }
    }

    // mostly what will be used
    protected double getStat(){
        return this.stat;
    }

    protected int getCost(){
        return this.cost;
    }

}
