package com.example.sauronsarmy.oopp.player;

/**
 * Created by Erik on 13/04/17.
 */

public interface PlayerModelInterface {

    void setLastLogOn(long newValue);
    long getLastLogOn();

    void setDamagePerSecond(int newValue);
    int getDamagePerSecond();

    void setDamage(int newValue);
    int getDamage();

    void removeMoney(int money);
    void addMoney(int money);
    int getMoney();

    void setState(java.util.Map value1);
    java.util.Map getState();

    void setMoneyPerSecond(int newValue);
    int getMoneyPerSecond();
}
