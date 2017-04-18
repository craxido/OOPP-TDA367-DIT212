package com.example.sauronsarmy.oopp;

/**
 * Created by Loke on 13/04/17.
 */

public interface PlayerModelInterface {

    PlayerModel getInstance();

    void setLastLogOn(long newValue);
    long getLastLogOn();

    void setDamageMultiplier(double newValue);
    double getDamageMultiplier();

    void setDamage(int newValue);
    int getDamage();

    void setMoney(int newValue);
    int getMoney();

    void setMoneyPerSecond(int newValue);
    int getMoneyPerSecond();
}
