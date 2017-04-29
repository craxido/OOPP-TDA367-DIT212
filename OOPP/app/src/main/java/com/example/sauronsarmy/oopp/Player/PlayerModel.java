package com.example.sauronsarmy.oopp.Player;

import java.util.*;
import java.util.Map;

/**
 * Created by Erik on 04/04/17.
 * Implemented the PlayerModel class using the singleton
 * pattern, since there should only be one instance
 * of a player.
 */

public class PlayerModel implements  PlayerModelInterface{

    private static final PlayerModel ourInstance = new PlayerModel();

    private int damage;
    private double damageMultiplier;
    private int money;
    private double moneyPerSecond;
    // This must be assigned when exiting the app.
    private long lastLogOn;

    public static PlayerModel getInstance() {
        return ourInstance;
    }

    /*
    Constructor is private since this implemented as a singelton.
    Only the model itself is allowed to create a new instance. Everyone
    else just gets to talk with this instance.
     */
    private PlayerModel() {
        damage           = 10;
        damageMultiplier = 1.0;
        money            = 50;
        moneyPerSecond   = 0;
    }

    public long getLastLogOn() {
        return lastLogOn;
    }

    public void setLastLogOn(long lastLogOn) {
        this.lastLogOn = lastLogOn;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(double damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
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

    public double getMoneyPerSecond() {
        return moneyPerSecond;
    }

    public void setMoneyPerSecond(double moneyPerSecond) {
        this.moneyPerSecond = moneyPerSecond;
    }

    @Override
    public void setState(Map newState) {
        setDamage(          (int) newState.get("damage"));
        setDamageMultiplier((double) newState.get("damageMult"));
        setMoney(           (int) newState.get("money"));
        setMoneyPerSecond(  (double) newState.get("moneyPerSec"));
        setLastLogOn(       (long) newState.get("lastLogOn"));
    }

    @Override
    public Map getState() {
        return new HashMap<String, Object>() {
            {
                put("damage",      getDamage());
                put("damageMult",  getDamageMultiplier());
                put("money",       getMoney());
                put("moneyPerSec", getMoneyPerSecond());
                put("lastLogOn",   getLastLogOn());
            }
        };
    }
}
