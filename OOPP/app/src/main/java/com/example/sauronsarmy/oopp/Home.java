package com.example.sauronsarmy.oopp;

/**
 * Created by bunnyfiscuit on 05/04/17.
 */

class Home{
    private static final Home homeInstance = new Home();

    private Player player = Player.getInstance();
    private Upgrade oilPumpUpgrade = new Upgrade(2, 5);
    private int oilPumpUpgradeCounter = 1;

    protected void buyOilPumpUpgrade(){
        player.setMoney(player.getMoney() - oilPumpUpgrade.getCurrentCost());
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
