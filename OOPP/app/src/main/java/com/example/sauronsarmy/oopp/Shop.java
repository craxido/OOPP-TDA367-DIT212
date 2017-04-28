package com.example.sauronsarmy.oopp;

import com.example.sauronsarmy.oopp.Player.PlayerModel;
import com.example.sauronsarmy.oopp.Player.PlayerModelInterface;

/**
 * Created by bunnyfiscuit on 05/04/17.
 */

class Shop {

    private static final Shop shopInstance = new Shop();

    // Variables
    private PlayerModelInterface player = PlayerModel.getInstance();

    private Upgrade damageUpgrade = new Upgrade(5,5);
    private Upgrade multiplierUpgrade = new Upgrade(0.1,5);
    private int damageUpgradeCounter = 1;
    private int multiplierUpgradeCounter = 1;

    static Shop getInstance() { return shopInstance; }

    /** Buy damage upgrade for player.
     *  adds to player damage, and applies the cost
     *  updates how many upgrades has been done, and updates the upgrade
     */
    protected boolean buyDamageUpgrade(){
        if(player.getMoney() >= damageUpgrade.getCost()) {
            player.setMoney(player.getMoney() - damageUpgrade.getCost());
            player.setDamage(player.getDamage() + (int) damageUpgrade.getStat());

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
     * Getters
     * @return
     */

    protected Upgrade getDamageUpgrade(){
        return damageUpgrade;
    }

    protected int getDamageUpgradeCounter(){
        return damageUpgradeCounter;
    }

    protected Upgrade getMultiplierUpgrade(){
        return multiplierUpgrade;
    }

    protected int getMultiplierUpgradeCounter(){
        return multiplierUpgradeCounter;
    }


}
