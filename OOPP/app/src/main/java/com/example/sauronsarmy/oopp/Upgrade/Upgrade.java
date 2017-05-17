package com.example.sauronsarmy.oopp.Upgrade;

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
        double f = 1.7;
        if(multiplier > 1) {
            f = stat * (f);
        }else if (multiplier > 10){
            f = stat * (f + 0.1); //1.8
        }else if (multiplier > 25){
            f = stat * (f + 0.2); //1.9
        }else if (multiplier > 50){
            f = stat * (f + 0.3); //2.0
        }else if (multiplier > 100){
            f = stat * (f + 0.5); //2.2
        }
        stat = (int) Math.ceil(f);
    }

    // Updates the cost for the upgrade with multiplier
    protected void updateCost(int multiplier){
        float f = 1.5f;
        if(multiplier > 1) {
            f = cost * 1.5f;
        }else if (multiplier > 10){
            f = cost * 1.7f;
        }else if (multiplier > 20){
            f = cost * 1.9f;
        }else if (multiplier > 40){
            f = cost * 2.1f;
        }else if (multiplier > 60){
            f = cost * 2.3f;
        }else if (multiplier > 80){
            f = cost * 2.5f;
        }else if (multiplier > 90){
            f = cost * 2.7f;
        }else if (multiplier > 100){
            f = cost * 2.9f;
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
