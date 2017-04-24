package com.example.sauronsarmy.oopp;

/**
 * Created by bunnyfiscuit on 05/04/17.
 */

class Home{
    private static final Home homeInstance = new Home();

    private PlayerModel player = PlayerModel.getInstance();
    private Upgrade oilPumpUpgrade = new Upgrade(0.5, 10);
    private int oilPumpUpgradeCounter = 1;

    static Home getInstance() {
        return homeInstance;
    }

    protected boolean buyOilPumpUpgrade(){
        if (player.getMoney() >= oilPumpUpgrade.getCost()) {
            player.setMoney(player.getMoney() - oilPumpUpgrade.getCost());
            player.setMoneyPerSecond(player.getMoneyPerSecond() + (int) oilPumpUpgrade.getStat());

            oilPumpUpgradeCounter++;
            oilPumpUpgrade.updateStat(oilPumpUpgradeCounter);
            oilPumpUpgrade.updateCost(oilPumpUpgradeCounter);

            return true;
        } else {
            return false;
        }

    }

    protected Upgrade getOilPumpUpgrade(){
        return oilPumpUpgrade;
    }

    protected int getOilPumpUpgradeCounter(){
        return oilPumpUpgradeCounter;
    }

}
