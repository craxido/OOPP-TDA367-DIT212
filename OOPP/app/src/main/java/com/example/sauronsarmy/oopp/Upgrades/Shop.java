package com.example.sauronsarmy.oopp.Upgrades;

import android.util.Log;

import com.example.sauronsarmy.oopp.Player.PlayerModel;
import com.example.sauronsarmy.oopp.Player.PlayerModelInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bunnyfiscuit on 05/04/17.
 */

public class Shop {

    private static final Shop shopInstance = new Shop();
    private static final String TAG = "Shop";

    // Variables
    private PlayerModelInterface player = PlayerModel.getInstance();

    private Upgrade damageUpgrade = new Upgrade(5,5);
    private Upgrade multiplierUpgrade = new Upgrade(1,5);
    private int damageUpgradeCounter = 1;
    private int multiplierUpgradeCounter = 1;

    public static Shop getInstance() { return shopInstance; }

    /** Buy damage upgrade for player.
     *  adds to player damage, and applies the cost
     *  updates how many upgrades has been done, and updates the upgrade
     */
    protected boolean buyDamageUpgrade(){
        if(player.getMoney() >= damageUpgrade.getCost()) {
            player.setMoney(player.getMoney() - damageUpgrade.getCost());
            player.setDamage(player.getDamage() + damageUpgrade.getStat());

            damageUpgradeCounter++;
            damageUpgrade.updateStat(damageUpgradeCounter);
            damageUpgrade.updateCost(damageUpgradeCounter);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Similar to above, except this upgrade is a multiplier.
     */
    protected boolean buyMultiplierUpgrade(){
        if (player.getMoney() >= multiplierUpgrade.getCost()) {
            player.setMoney(player.getMoney() - multiplierUpgrade.getCost());
            player.setDamageMultiplier(player.getDamageMultiplier() + multiplierUpgrade.getStat());

            multiplierUpgradeCounter++;
            multiplierUpgrade.updateStat(multiplierUpgradeCounter);
            multiplierUpgrade.updateCost(multiplierUpgradeCounter);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Setters & Getters
     */

    public Upgrade getDamageUpgrade(){
        return damageUpgrade;
    }

    public int getDamageUpgradeCounter(){
        return damageUpgradeCounter;
    }

    public Upgrade getMultiplierUpgrade(){
        return multiplierUpgrade;
    }

    public int getMultiplierUpgradeCounter(){
        return multiplierUpgradeCounter;
    }

    public void setUpgradeCounters(Map<String, Integer> map){
        Log.i(TAG, "Loading previous upgrade state");
        damageUpgradeCounter = map.get("dmgUpgrade");
        multiplierUpgradeCounter = map.get("multUpgrade");
    }

    public Map getUpgradeCounters(){
        return new HashMap<String, Integer>(){
            {
                put("dmgUpgrade", getDamageUpgradeCounter());
                put("multUpgrade", getMultiplierUpgradeCounter());
            }
        };
    }

}
