package com.example.sauronsarmy.oopp.map;

import com.example.sauronsarmy.oopp.areaType;

/**
 * @author Jonatan Källman
 */

public class Area {

    private int imgRef;
    private com.example.sauronsarmy.oopp.areaType areaType;
    private Level[] levels;
    private Level currentLevel;
    private boolean finished=false;

    public int getAreaIndex() {
        return areaIndex;
    }

    private int areaIndex;


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

    public Level[] getLevels() {
        return levels;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    void setCurrentLevel(Level level){
        this.currentLevel = level;
    }

    void checkComplete(){
        boolean test = true;
        //Check if all levels in an area is complete
        for (Level lvl :levels){
            if(lvl !=null) {
                test &= lvl.getComplete();
            }
        }
        finished=test;

    }

    public boolean getComplete(){
        return finished;
    }

    public Level getLevel(int index) {
        if (index < levels.length) {

            return levels[index];
        }

        return null;
    }

}
