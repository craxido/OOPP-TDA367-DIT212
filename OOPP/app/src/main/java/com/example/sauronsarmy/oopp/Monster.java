package com.example.sauronsarmy.oopp;

import android.graphics.drawable.Drawable;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;

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
        boolean temp = this.health <= 0;
        return temp;
    }


}
