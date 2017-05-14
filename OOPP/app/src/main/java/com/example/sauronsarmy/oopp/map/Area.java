package com.example.sauronsarmy.oopp.map;

/**
 * @author Jonatan Källman
 */

class Area {

    private int imgRef;
    private areaType areaType;
    private Level[] levels;
    private Level currentLevel;
    private int areaIndex;
    private MapPresenter map;

    Area(int imgRef, areaType area, Level[] levels, int areaIndex){
        this.imgRef = imgRef;
        this.areaType=area;
        this.levels=levels;
        this.currentLevel=levels[0];
        this.areaIndex=areaIndex;
    }

    areaType getAreaType(){
        return this.areaType;
    }

    int getImgRef() {
        return imgRef;
    }

    void setImgRef(int imgRef) {
        this.imgRef = imgRef;
    }

    Level[] getLevels() {
        return levels;
    }

    Level getCurrentLevel() {
        return currentLevel;
    }

    void setCurrentLevel(Level level){
        this.currentLevel = level;
    }

    int getAreaIndex(Area area){
        return area.areaIndex;
    }
}
