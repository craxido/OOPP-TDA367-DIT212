package com.example.sauronsarmy.oopp;

import java.util.Date;

/**
 * Created by Erik on 04/04/17.
 * Implemented the Player class using the singleton
 * pattern, since there should only be one instance
 * of a player.
 */

class Player {
    private static final Player ourInstance = new Player();

    private int damage;
    private double damageMutiplier;
    private int money;
    private int moneyPerSecond;
    // This must be assigned when exiting the app.
    private long lastLogOn;

    static Player getInstance() {
        return ourInstance;
    }

    private Player() {
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

    public double getDamageMutiplier() {
        return damageMutiplier;
    }

    public void setDamageMutiplier(double damageMutiplier) {
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
