package com.example.sauronsarmy.oopp.Map;
import java.util.Arrays;
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

    boolean equals(Area other){
        return (this.imgRef==other.imgRef && this.areaType==other.areaType
                && this.levelsEquals(other.levels) && this.currentLevel.equals(other.currentLevel)
                && this.areaIndex == other.areaIndex);
        }

    private boolean levelsEquals(Level[] otherLevels){
        boolean isEqual=true;
        for (int i=0; i < this.levels.length; i++){
            if(!this.levels[i].equals(otherLevels[i])){
                isEqual=false;
                break;
            }
        }
        return isEqual;
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
