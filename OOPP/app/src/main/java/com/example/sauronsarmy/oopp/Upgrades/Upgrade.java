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
        float f = 1.2f;
        if(multiplier > 1) {
            f = stat * 1.2f;
        }else if (multiplier > 10){
            f = stat * 1.3f;
        }else if (multiplier > 20){
            f = stat * 1.4f;
        }else if (multiplier > 40){
            f = stat * 1.5f;
        }else if (multiplier > 60){
            f = stat * 1.6f;
        }else if (multiplier > 80){
            f = stat * 1.7f;
        }else if (multiplier > 90){
            f = stat * 1.8f;
        }else if (multiplier > 100){
            f = stat * 1.9f;
        }
        stat = Math.round(f);
    }

    // Updates the cost for the upgrade with multiplier
    protected void updateCost(int multiplier){
        float f = 1.5f;
        if(multiplier > 1) {
            f = cost * 1.5f;
        }else if (multiplier > 10){
            f = cost * 1.6f;
        }else if (multiplier > 20){
            f = cost * 1.7f;
        }else if (multiplier > 40){
            f = cost * 1.8f;
        }else if (multiplier > 60){
            f = cost * 1.9f;
        }else if (multiplier > 80){
            f = cost * 2.0f;
        }else if (multiplier > 90){
            f = cost * 2.1f;
        }else if (multiplier > 100){
            f = cost * 2.2f;
        }
        cost = Math.round(f);
    }

    public int getStat(){
        return stat;

    }

    public int getCost(){
        return this.cost;
    }

    public Map getUpgradeStats(){
        return new HashMap<String, Integer> () {
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
