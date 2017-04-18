package com.example.sauronsarmy.oopp;

/**
 * Created by Erik on 13/04/17.
 */

public class StatsPresenter implements StatsPresenterInterface {

    private PlayerModelInterface playerModel;

    StatsPresenter() {
        playerModel = playerModel.getInstance();
    }

    @Override
    public int getMoneyPerSecond() {
        return playerModel.getMoneyPerSecond();
    }

    @Override
    public int getMoneyAmount() {
        return playerModel.getMoney();
    }

    @Override
    public int getPlayerDamage() {
        return playerModel.getDamage();
    }

    @Override
    public double getPlayerDamageMultiplier() {
        return playerModel.getDamageMultiplier();
    }
}
