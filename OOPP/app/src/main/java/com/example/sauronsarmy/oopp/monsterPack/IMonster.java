package com.example.sauronsarmy.oopp.monsterPack;

/**
 * Created by Jonatan on 18/05/2017.
 */

public interface IMonster {

    boolean equals(IMonster other);
    int getHealth();
    int getMaxHealth();
    int getGold();
    int getImageRef();
    boolean damageMonster(int damage);
    void setHealth(int health);
    boolean isBoss();

}
