package com.example.sauronsarmy.oopp;

/**
 * Created by bunnyfiscuit on 05/04/17.
 */

class Shop {

    // Variables
    private PlayerModel player;
    private Upgrade damageUpgrade;
    private Upgrade multiplierUpgrade;
    private int damageUpgradeCounter = 1;
    private int multiplierUpgradeCounter = 1;

    protected Shop(){
        this.player = PlayerModel.getInstance();
        this.damageUpgrade = new Upgrade(5, 5);
        this.multiplierUpgrade = new Upgrade(0.1, 5);
    }

    /** Buy damage upgrade for player.
     *  adds to player damage, and applies the cost
     *  updates how many upgrades has been done, and updates the upgrade
     */
    protected void buyDamageUpgrade(){
        player.setMoney(player.getMoney() - damageUpgrade.getCurrentCost());
        player.setDamage(player.getDamage() + (int) damageUpgrade.getCurrentStat());

        damageUpgradeCounter++;
        damageUpgrade.updateStat(damageUpgradeCounter);
        damageUpgrade.updateCost(damageUpgradeCounter);

    }

    /**
     * Similar to above, except this upgrade is a multiplier.
     */
    protected void buyMultiplierUpgrade(){
        player.setMoney(player.getMoney() - multiplierUpgrade.getCurrentCost());
        player.setDamageMutiplier(player.getDamageMutiplier() + multiplierUpgrade.getCurrentStat());

        multiplierUpgradeCounter++;
        multiplierUpgrade.updateStat(multiplierUpgradeCounter);
        multiplierUpgrade.updateCost(multiplierUpgradeCounter);
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
