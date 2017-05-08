package com.example.sauronsarmy.oopp.Upgrades;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bunnyfiscuit on 05/04/17.
 */

public class Upgrade {

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
    public int getStat(){
        return stat;

    }

    public int getCost(){
        return this.cost;
    }

    public Map getUpgradeStats(){
        return new HashMap<String, Object> () {
            {
                put("stat", getStat());
                put("cost", getCost());
            }
        };
    }

    public void setUpgradeStats(Map<String, Integer> map){
        stat = map.get("stat");
        cost = map.get("cost");
    }

}
