package com.example.sauronsarmy.oopp.map;

import com.example.sauronsarmy.oopp.areaType;
import com.example.sauronsarmy.oopp.monsterPack.IMonster;
import com.example.sauronsarmy.oopp.monsterPack.monsterFactory;

/**
 * @author Jonatan Källman
 * Class for the Level(s) inside the Areas.
 */
public class Level {

    private IMonster monster;
    private int healthMultiplier;
    private int goldMultiplier;
    private areaType areaType;
    int goal = 10;
    int pathToGoal = 0;
    private boolean completed = false;
    private boolean checked = false;
    private int levelIndex;

    /**
     * Constructor for the Levels contained in an Area.
     * @param monster: A Monster for the Level.
     * @param healthMultiplier: The monster health multiplier for the level.
     * @param goldMultiplier: The monster gold multiplier for the level.
     * @param area: The areaType of the Area that the Level belongs to.
     * @param levelIndex: The index of the Level.
     */
    Level(IMonster monster, int healthMultiplier, int goldMultiplier, areaType area, int levelIndex) {
        this.monster = monster;
        this.healthMultiplier = healthMultiplier;
        this.goldMultiplier = goldMultiplier;
        this.areaType = area;
        this.levelIndex = levelIndex;
    }
    /**
     *  Equals method for Level. Used to compare one Level to another.
     */
    public boolean equals(Level other) {
        return (this.monster.equals(other.monster)) && (this.healthMultiplier == other.healthMultiplier)
                && (this.goldMultiplier == other.goldMultiplier) && (this.areaType == other.areaType)
                && (this.levelIndex == other.levelIndex);
    }

    void setChecked(boolean bool) {
        checked = bool;
    }

    boolean isChecked() {
        return this.checked;
    }

    int getLevelIndex(){
        return this.levelIndex;
    }

    areaType getArea() {
        return this.areaType;
    }

    void setArea(areaType areaType) {
        this.areaType = areaType;
    }

    int getHealthMultiplier() {
        return healthMultiplier;
    }

    void setHealthMultiplier(int healthMultiplier) {
        this.healthMultiplier = healthMultiplier;
    }

    int getGoldMultiplier() {
        return goldMultiplier;
    }

    void setGoldMultiplier(int goldMultiplier) {
        this.goldMultiplier = goldMultiplier;
    }

    IMonster getCurrentMonster() {
        if (monster == null) {
            setNewMonster();
        }
        return monster;
    }

    private void setCurrentMonster(IMonster currentMonster) {
        this.monster = currentMonster;
    }

    /**
     *  Method used to damage the current monster of the area.
     *  Returns the monster's gold if the monster's health is =< 0.
     *  Also sets up a new monster.
     */
    int damageMonster(int damage) {
        int ret = -1;
        //If the monster died, update pathToGoal, the return value and set a new monster
        if (monster.damageMonster(damage)) {

            ret = monster.getGold();
            pathToGoal++;

            if (pathToGoal >= goal) {
                completed = true;
                pathToGoal = goal;
            }
            setNewMonster();

        }
        return ret;
    }

    /**
     *  Sets a monster or a boss monster depending on the progress of the Level.
     */
    void setNewMonster() {

        monsterFactory monFac = new monsterFactory();
        if (!(pathToGoal == 9)) {
            setCurrentMonster(monFac.getMonster(getHealthMultiplier(), getGoldMultiplier() * 10, getArea()));
        } else { //It's time for a boss monster!
            setCurrentMonster(monFac.getBossMonster(getHealthMultiplier() * 10, getGoldMultiplier() * 100));
        }
    }


    int getGoal() {
        return goal;
    }

    int getPathToGoal() {
        return pathToGoal;
    }

    void setComplete(boolean bool) {
        completed = bool;
    }

    public boolean getComplete() {
        return completed;
    }

}
