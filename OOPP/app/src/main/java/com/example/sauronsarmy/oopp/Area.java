package com.example.sauronsarmy.oopp;

import android.media.Image;

/**
 * Created by Jonatan on 2017-04-05.
 */

class Area {
    private Image img;
    private areaType areaType;
    private Level[] levels;

    public Area(Image img, areaType area, Level[] levels){
        this.img=img;
        this.areaType=area;
        this.levels=levels;
    }

    public areaType getArea(){
        return this.areaType;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Level[] getLevels() {
        return levels;
    }

}
