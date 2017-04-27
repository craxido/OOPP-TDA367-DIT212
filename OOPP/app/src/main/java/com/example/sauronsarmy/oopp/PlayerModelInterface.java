package com.example.sauronsarmy.oopp;

/**
 * Created by Loke on 13/04/17.
 */

public interface PlayerModelInterface {

    void setLastLogOn(long newValue);
    long getLastLogOn();

    void setDamageMultiplier(float newValue);
    float getDamageMultiplier();

    void setDamage(int newValue);
    int getDamage();

    void setMoney(int newValue);
    int getMoney();

    void setState(java.util.Map value1);
    java.util.Map getState();

    void setMoneyPerSecond(double newValue);
    double getMoneyPerSecond();
}
