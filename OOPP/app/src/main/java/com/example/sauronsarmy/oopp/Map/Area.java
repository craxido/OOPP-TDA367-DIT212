package com.example.sauronsarmy.oopp.Map;

/**
 * Author: Jonatan Källman
 */

public class Area {

    private int imgRef;
    private areaType areaType;
    private Level[] levels;
    private Level currentLevel;

    Area(int imgRef, areaType area, Level[] levels){
        this.imgRef = imgRef;
        this.areaType=area;
        this.levels=levels;
        this.currentLevel=levels[0];
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

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level level){

        this.currentLevel = level;
    }
}
