package com.example.sauronsarmy.oopp.monsterPack;

/**
 * @author Jonatan Källman
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
