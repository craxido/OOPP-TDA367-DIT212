package com.example.sauronsarmy.oopp.Upgrades;

import com.example.sauronsarmy.oopp.Player.PlayerModel;
import com.example.sauronsarmy.oopp.Player.PlayerModelInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bunnyfiscuit on 05/04/17.
 */

public class Shop {

    private static final Shop shopInstance = new Shop();

    // Variables
    private PlayerModelInterface player = PlayerModel.getInstance();

    private Upgrade damageUpgrade = new Upgrade(5,5);
    private Upgrade multiplierUpgrade = new Upgrade(1,5);
    private int damageUpgradeCounter = 1;
    private int multiplierUpgradeCounter = 1;

    public static Shop getInstance() { return shopInstance; }



    protected void makePayment(int i){
        switch(i){
            case 0:
                player.setMoney(player.getMoney() - damageUpgrade.getCost());
                break;
            case 1:
                player.setMoney(player.getMoney() - multiplierUpgrade.getCost());
                break;

        }
    }

    /** Buy damage upgrade for player.
     *  adds to player damage, and applies the cost
     *  updates how many upgrades has been done, and updates the upgrade
     */
    protected boolean upgradeDamage(){
        if(player.getMoney() >= damageUpgrade.getCost()) {
            makePayment(0);
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
    protected boolean upgradeMultiplier(){
        if (player.getMoney() >= multiplierUpgrade.getCost()) {
            makePayment(1);
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
        damageUpgradeCounter = map.get("damage");
        multiplierUpgradeCounter = map.get("multiplier");
    }

    public Map getUpgradeCounters(){
        return new HashMap<String, Object>(){
            {
                put("damage", getDamageUpgradeCounter());
                put("multiplier", getMultiplierUpgradeCounter());
            }
        };
    }

}
