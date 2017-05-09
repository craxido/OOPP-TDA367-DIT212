package com.example.sauronsarmy.oopp;

/**
 * Created by bunnyfiscuit on 05/04/17.
 */

class Upgrade {

    private int stat;
    private int cost;

    protected Upgrade(int baseStat, int baseCost){
        this.stat = baseStat;
        this.cost = baseCost;

    }

    // Updates current stat upgrade amount with a multiplier
    protected void updateStat(int multiplier){
        stat += (multiplier/10);
    }

    // Updates the cost for the upgrade with multiplier
    protected void updateCost(int multiplier){
        if(multiplier > 1) {
            cost += Math.round(multiplier / 2);
        }
    }

    // mostly what will be used
    protected int getStat(){
        return (int) (Math.round(stat * 100) / 100.0);

    }

    protected int getCost(){
        return this.cost;
    }

}
