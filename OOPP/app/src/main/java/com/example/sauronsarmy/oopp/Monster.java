package com.example.sauronsarmy.oopp;

import android.graphics.drawable.Drawable;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;

/**
 * Created by Filip on 2017-04-04.
 */

class Monster {

    private double health;
    private double maxhealth;
    private int gold;
    private int imageref;


    public Monster(double health,int gold,int imageref){
        this.maxhealth = health;
        this.health = health;
        this.gold = gold;
        this.imageref = imageref;
    }

    public double getHealth(){
        return this.health;
    }

    public double getMaxhealth(){
        return this.maxhealth;
    }


    public int getGold(){
        return this.gold;
    }

    public int getImageRef(){
        return this.imageref;
    }

    //damage a monster, return true if dead
    public boolean damageMonster(double damage){
        this.health -= damage;
        return this.health <= 0;
    }


}
