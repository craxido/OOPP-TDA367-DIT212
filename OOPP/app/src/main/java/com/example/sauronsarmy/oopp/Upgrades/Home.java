package com.example.sauronsarmy.oopp.Upgrades;

import android.util.Log;

import com.example.sauronsarmy.oopp.Player.PlayerModel;
import com.example.sauronsarmy.oopp.Player.PlayerModelInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bunnyfiscuit on 05/04/17.
 */

class Home implements HomeMVPInterface.Model  {
    private static final String TAG = "Home";
    private static final Home homeInstance = new Home();

    private PlayerModelInterface player = PlayerModel.getInstance();
    private Upgrade oilPumpUpgrade = new Upgrade(1, 100);
    private int oilPumpUpgradeCounter = 1;

    public static Home getInstance() {
        return homeInstance;
    }

    private void makePayment() {
        player.setMoney(player.getMoney() - oilPumpUpgrade.getCost());
    }

    public boolean buyOilPumpUpgrade() {
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
        Log.i(TAG, "Setting old Home state");
        oilPumpUpgradeCounter = map.get("oil");
    }

    //same as above except without Log. for tests
    public void testOilPumpUpgradeCounter(Map<String, Integer> map){
        oilPumpUpgradeCounter = map.get("oil");
    }

    //for tests
    public void setOilCounter(int i){
        oilPumpUpgradeCounter = i;
    }

    public Map getUpgradeCounters(){
        return new HashMap<String, Integer> ()
        {
            {
                put("oil", oilPumpUpgradeCounter);
            }
        };
    }

    public int getPlayerMoneyPerSec(){
        return player.getMoneyPerSecond();
    }

    public int getPlayerMoney(){
        return player.getMoney();
    }

}
