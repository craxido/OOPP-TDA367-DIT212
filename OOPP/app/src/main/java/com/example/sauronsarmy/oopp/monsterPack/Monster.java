package com.example.sauronsarmy.oopp.monsterPack;

/**
 * Created by Filip on 2017-04-04.
 */

public class Monster implements IMonster {

    private int health;
    private int maxhealth;
    private int gold;
    private int imageref;
    private boolean isBoss;


    public Monster(int health, int gold, int imageref, boolean boss){
        this.maxhealth = health;
        this.health = health;
        this.gold = gold;
        this.imageref = imageref;
        this.isBoss = boss;
    }

    @Override
    public boolean equals(Monster other){
        return this.maxhealth == other.maxhealth && this.health == other.health
                && this.gold == other.gold && this.imageref == other.imageref;
    }

    @Override
    public int getHealth(){
        return this.health;
    }

    @Override
    public int getMaxHealth(){
        return this.maxhealth;
    }

    @Override
    public int getGold(){
        return this.gold;
    }

    @Override
    public int getImageRef(){
        return this.imageref;
    }

    //damage a monster, return true if dead
    @Override
    public boolean damageMonster(int damage){
        this.health -= damage;
        return this.health <= 0;
    }

    @Override
    public void setHealth(int health){
        this.health=health;
    }

    @Override
    public boolean isBoss(){
        return isBoss;
    }
}
