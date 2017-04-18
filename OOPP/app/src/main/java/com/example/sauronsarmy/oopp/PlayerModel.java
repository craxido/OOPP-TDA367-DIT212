package com.example.sauronsarmy.oopp;

/**
 * Created by Erik on 04/04/17.
 * Implemented the PlayerModel class using the singleton
 * pattern, since there should only be one instance
 * of a player.
 */

class PlayerModel implements  PlayerModelInterface{
    private static final PlayerModel ourInstance = new PlayerModel();

    private int damage;
    private double damageMutiplier;
    private int money;
    private int moneyPerSecond;
    // This must be assigned when exiting the app.
    private long lastLogOn;

    @Override
    static PlayerModel getInstance() {
        return ourInstance;
    }

    private PlayerModel() {
        damage          = 10;
        damageMutiplier = 1;
        money           = 0;
        moneyPerSecond  = 0;
    }

    public long getLastLogOn() {
        return lastLogOn;
    }

    public void setLastLogOn(long lastLogOn) {
        this.lastLogOn = lastLogOn;
    }

    public double getDamageMultiplier() {
        return damageMutiplier;
    }

    public void setDamageMultiplier(double damageMutiplier) {
        this.damageMutiplier = damageMutiplier;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoneyPerSecond() {
        return moneyPerSecond;
    }

    public void setMoneyPerSecond(int moneyPerSecond) {
        this.moneyPerSecond = moneyPerSecond;
    }
}
