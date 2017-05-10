package com.example.sauronsarmy.oopp.Upgrades;

import com.example.sauronsarmy.oopp.Player.PlayerModel;
import com.example.sauronsarmy.oopp.Player.PlayerModelInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bunnyfiscuit on 05/04/17.
 */

public class Home {
    private static final Home homeInstance = new Home();

    private PlayerModelInterface player = PlayerModel.getInstance();
    private Upgrade oilPumpUpgrade = new Upgrade(1, 10);
    private int oilPumpUpgradeCounter = 1;

    public static Home getInstance() {
        return homeInstance;
    }

    protected void makePayment() {
        player.setMoney(player.getMoney() - oilPumpUpgrade.getCost());
    }

    protected boolean buyOilPumpUpgrade() {
        if (player.getMoney() >= oilPumpUpgrade.getCost()) {
            makePayment();
            player.setMoneyPerSecond(player.getMoneyPerSecond() + oilPumpUpgrade.getStat());
            oilPumpUpgradeCounter++;
            oilPumpUpgrade.updateStat(oilPumpUpgradeCounter);
            oilPumpUpgrade.updateCost(oilPumpUpgradeCounter);

            return true;
        } else {
            return false;
        }

    }

    public Upgrade getOilPumpUpgrade() {
        return oilPumpUpgrade;
    }

    public int getOilPumpUpgradeCounter() {
        return oilPumpUpgradeCounter;
    }

    public void setOilPumpUpgradeCounter(Map<String, Integer> map) {
        oilPumpUpgradeCounter = map.get("oil");
    }

    public Map getUpgradeCounters(){
        return new HashMap<String, Integer> ()
        {
            {
                put("oil", oilPumpUpgradeCounter);
            }
        };
    }


}
