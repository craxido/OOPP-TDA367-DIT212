package com.example.sauronsarmy.oopp;

import android.media.Image;

/**
 * Created by Jonatan on 2017-04-05.
 */

class Area {
    private static final Area area1Instance = new Area(int imgref, enum areaType.MOUNTAIN, Level[] area1Levels);
    private static final Area area2Instance = new Area(int imgref, enum areaType.FOREST, Level[] area2Levels);
    private static final Area area3Instance = new Area(int imgref, enum areaType.VOLCANO, Level[] area3Levels);

    private int imgRef;
    private areaType areaType;
    private Level[] levels;

    public Area(int imgRef, areaType area, Level[] levels){
        this.imgRef = imgRef;
        this.areaType=area;
        this.levels=levels;
    }

    public areaType getArea(){
        return this.areaType;
    }

    public int getImgRef() {
        return imgRef;
    }

    public void setImgRef(int imgRef) {
        this.imgRef = imgRef;
    }

    public Level[] getLevels() {
        return levels;
    }

}
