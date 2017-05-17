package com.example.sauronsarmy.oopp.monsterPack;

/**
 * Created by Filip on 2017-04-04.
 */

public class Monster {

    private int health;
    private int maxhealth;
    private int gold;
    private int imageref;


    public Monster(int health, int gold, int imageref){
        this.maxhealth = health;
        this.health = health;
        this.gold = gold;
        this.imageref = imageref;
    }

    public boolean equals(Monster other){
        return this.maxhealth == other.maxhealth && this.health == other.health
                && this.gold == other.gold && this.imageref == other.imageref;
    }

    public int getHealth(){
        return this.health;
    }

    public int getMaxhealth(){
        return this.maxhealth;
    }


    public int getGold(){
        return this.gold;
    }

    public int getImageRef(){
        return this.imageref;
    }

    //damage a monster, return true if dead
    public boolean damageMonster(int damage){
        this.health -= damage;
        return this.health <= 0;
    }


}
