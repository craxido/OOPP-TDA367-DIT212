package com.example.sauronsarmy.oopp.Upgrades;

import com.example.sauronsarmy.oopp.Player.PlayerModel;
import com.example.sauronsarmy.oopp.Player.PlayerModelInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bunnyfiscuit on 05/04/17.
 */

class Shop implements ShopMVPInterface.Model {

    private static final Shop shopInstance = new Shop();

    // Variables
    private PlayerModelInterface player = PlayerModel.getInstance();

    private Upgrade damageUpgrade = new Upgrade(5,5);
    private Upgrade dpsUpgrade = new Upgrade(1,5);
    private int damageUpgradeCounter = 1;
    private int dpsUpgradeCounter = 1;

    public static Shop getInstance() { return shopInstance; }

    /** Buy damage upgrade for player.
     *  adds to player damage, and applies the cost
     *  updates how many upgrades has been done, and updates the upgrade
     */
    public boolean buyDamageUpgrade(){
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
     * Similar to above, except this upgrade is for damage per second.
     */
    public boolean buyDPSUpgrade(){
        if (player.getMoney() >= dpsUpgrade.getCost()) {
            player.setMoney(player.getMoney() - dpsUpgrade.getCost());
            player.setDamagePerSecond(player.getDamagePerSecond() + dpsUpgrade.getStat());

            dpsUpgradeCounter++;
            dpsUpgrade.updateStat(dpsUpgradeCounter);
            dpsUpgrade.updateCost(dpsUpgradeCounter);
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

    public Upgrade getDPSUpgrade(){
        return dpsUpgrade;
    }

    public int getDPSUpgradeCounter(){
        return dpsUpgradeCounter;
    }

    public void setUpgradeCounters(HashMap<String, Integer> map){
        damageUpgradeCounter = map.get("damage");
        dpsUpgradeCounter = map.get("dps");
    }

    public Map getUpgradeCounters(){
        return new HashMap<String, Integer>(){
            {
                put("damage", getDamageUpgradeCounter());
                put("dps", getDPSUpgradeCounter());
            }
        };
    }

}
