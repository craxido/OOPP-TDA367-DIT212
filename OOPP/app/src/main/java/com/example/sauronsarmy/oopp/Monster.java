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
    private Image img;
    private String bild;
    private int draw;


    public Monster(double health,int gold,Image img,String bild,int draw){
        this.maxhealth = health;
        this.health = health;
        this.gold = gold;
        this.img = img;
        this.bild = bild;
        this.draw = draw;
    }
    //damage a monster, return true if dead
    public boolean damageMonster(double damage){

        this.health -= damage;
        return this.health <= 0;

    }

    public double getHealth(){
        return this.health;
    }

    public double getMaxhealth(){return this.maxhealth;}

    public String getBild(){return this.bild;}

    public int getGold(){
        return this.gold;
    }

    public int getDraw(){return this.draw;}

    public Image getImg(){

        return this.img;
    }
}
