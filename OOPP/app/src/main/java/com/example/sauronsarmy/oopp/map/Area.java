package com.example.sauronsarmy.oopp.map;

import com.example.sauronsarmy.oopp.areaType;

/**
 * @author Jonatan Källman
 * Class for the Areas containing Levels and different backgrounds.
 */
public class Area {

    private int imgRef;
    private com.example.sauronsarmy.oopp.areaType areaType;
    private Level[] levels;
    private Level currentLevel;
    private boolean finished=false;
    private int areaIndex;

    /**
     * Constructor for the Areas on the Map.
     * @param imgRef: The background of the Area, stored as a reference.
     * @param area: The areaType (ENUM), for an example "VOLCANO".
     * @param levels: The Level(s) of the Area.
     * @param areaIndex: The index of the Area.
     */
    Area(int imgRef, areaType area, Level[] levels, int areaIndex){
        this.imgRef = imgRef;
        this.areaType=area;
        this.levels=levels;
        this.currentLevel=levels[0];
        this.areaIndex=areaIndex;
    }

    /**
     *  Equals method for Area. Used to compare one area to another.
     */
    boolean equals(Area other){
        return (this.imgRef==other.imgRef && this.areaType==other.areaType
                && this.levelsEquals(other.levels) && this.currentLevel.equals(other.currentLevel)
                && this.areaIndex == other.areaIndex);
        }

    /**
     * Equals method for the Levels inside the Areas. Used in the other equals method.
     */
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

    /**
     *  Method that checks if all levels in an Area are completed.
     */
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

    /**
     * Returns if the Area is completed (true/ false).
     */
    public boolean getComplete(){
        return finished;
    }

    /**
     * Gets a Level from the Area using the given index.
     */
    public Level getLevel(int index) {
        if (index < levels.length) {

            return levels[index];
        }

        return null;
    }

    /**
     *  Method that sets a level, given the index of the level, to completed.
     */
    void completeLevel(int levelIndex){
        this.levels[levelIndex].setComplete(true);
        this.levels[levelIndex].setChecked(true);
        this.levels[levelIndex].pathToGoal =this.levels[levelIndex].goal;
    }


    public int getAreaIndex() {
        return areaIndex;
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

}
