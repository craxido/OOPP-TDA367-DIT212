package com.example.sauronsarmy.oopp;

/**
 * Created by bunnyfiscuit on 05/04/17.
 */

class Home{

    private Player player;
    private Upgrade oilPumpUpgrade;
    private int oilPumpUpgradeCounter = 1;

    protected Home(Player player){
        this.player = player;
        this.oilPumpUpgrade = new Upgrade(2, 5);
    }

    protected void buyOilPumpUpgrade(){
        player.setMoney(player.getMoney() - (int) oilPumpUpgrade.getCurrentCost());
        player.setMoneyPerSecond(player.getMoneyPerSecond() + (int) oilPumpUpgrade.getCurrentStat());

        oilPumpUpgradeCounter++;
        oilPumpUpgrade.updateStat(oilPumpUpgradeCounter);
        oilPumpUpgrade.updateCost(oilPumpUpgradeCounter);

    }

    protected Upgrade getOilPumpUpgrade(){
        return oilPumpUpgrade;
    }

    protected int getOilPumpUpgradeCounter(){
        return oilPumpUpgradeCounter;
    }

}
