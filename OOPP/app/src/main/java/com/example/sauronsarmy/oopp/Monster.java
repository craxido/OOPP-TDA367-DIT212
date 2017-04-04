package com.example.sauronsarmy.oopp;

import android.media.Image;

/**
 * Created by Filip on 2017-04-04.
 */

class Monster {

    private double health;
    private int gold;
    private Image img;


    public Monster(double health,int gold,Image img){

        this.health = health;
        this.gold = gold;
        this.img = img;

    }
    //damage a monster, return true if dead
    public boolean damageMonster(double damage){

        this.health -= damage;
        return this.health <= 0;

    }

    public double getHealth(){
        return this.health;
    }

    public int getGold(){
        return this.gold;
    }

    public Image getImg(){

        return this.img;
    }
}
