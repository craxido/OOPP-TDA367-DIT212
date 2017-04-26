package com.example.sauronsarmy.oopp;

/**
 * Created by Jonatan on 2017-04-05.
 */

class Area {

    private int imgRef;
    private areaType areaType;
    private Level[] levels;

    public Area(int imgRef, areaType area, Level[] levels){
        this.imgRef = imgRef;
        this.areaType=area;
        this.levels=levels;
    }

    public areaType getAreaType(){
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
