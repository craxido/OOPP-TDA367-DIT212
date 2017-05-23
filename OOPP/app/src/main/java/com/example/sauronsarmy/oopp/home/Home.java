package com.example.sauronsarmy.oopp.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.sauronsarmy.oopp.R;
import com.example.sauronsarmy.oopp.player.PlayerModelInterface;
import com.example.sauronsarmy.oopp.upgrade.Upgrade;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bunnyfiscuit on 05/04/17.
 */

class Home implements HomeMVPInterface.Model  {
    private static final String TAG = "Home";
    private static final Home homeInstance = new Home();
    private SharedPreferences saveState;

    private Upgrade oilPumpUpgrade = new Upgrade(1, 100);
    private int oilPumpUpgradeCounter = 1;

    public static Home getInstance() {
        return homeInstance;
    }

    public boolean buyOilPumpUpgrade(PlayerModelInterface player) {
        if (player.getMoney() >= oilPumpUpgrade.getCost()) {
            player.removeMoney(oilPumpUpgrade.getCost());
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

    @Override
    public void saveState(Context context) {
        Log.i(TAG, "Saving home state");
        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier),
                                                 Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveState.edit();
        editor.putInt("oil", getOilPumpUpgradeCounter());
        editor.putInt("oilStat", getOilPumpUpgrade().getStat());
        editor.putInt("oilCost", getOilPumpUpgrade().getCost());
        editor.apply();
    }

    @Override
    public void loadState(Context context) {
        Log.i(TAG, "Loading home state");
        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier),
                Context.MODE_PRIVATE);
        oilPumpUpgradeCounter = saveState.getInt("oil", 1);
        oilPumpUpgrade.setStat(saveState.getInt("oilStat",1));
        oilPumpUpgrade.setCost(saveState.getInt("oilCost",100));
    }
}
