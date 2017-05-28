package com.example.sauronsarmy.oopp.map;

import com.example.sauronsarmy.oopp.areaType;
import com.example.sauronsarmy.oopp.monsterPack.monsterFactory;

/**
 * @author Jonatan Källman
 * A Factory for creating Levels.
 */
class levelFactory {

    levelFactory() {
    }

    private monsterFactory monfac = new monsterFactory();


    Level getMountainLevel(int levelIndex){
        return new Level(monfac.getMountainMonster(100 * (levelIndex + 1), 100),
                100 * (levelIndex + 1), 10 * (levelIndex + 1), areaType.MOUNTAIN, levelIndex);
    }

    Level getForestLevel(int levelIndex){
        return new Level(monfac.getForestMonster(20000 * (levelIndex + 1), 500),
                20000 * (levelIndex + 1), 20 * (levelIndex + 1), areaType.FOREST, levelIndex);
    }

    Level getVolcanoLevel(int levelIndex){
        return new Level(monfac.getVolcanoMonster(300000 * (levelIndex + 1), 3000),
                300000 * (levelIndex + 1), 30 * (levelIndex + 1), areaType.VOLCANO, levelIndex);
    }

    /**
     *  Creates the Levels for the mountain area.
     */
    Level[] getMountainLevels() {
        int LevelAmount = 3;
        Level[] levels = new Level[LevelAmount];
        for (int i = 0; i < LevelAmount; i++) {
            levels[i] = getMountainLevel(i);
        }
        return levels;
    }

    /**
     *  Creates the Levels for the forest area.
     */
    Level[] getForestLevels() {
        int LevelAmount = 3;
        Level[] levels = new Level[LevelAmount];
        for (int i = 0; i < LevelAmount; i++) {
            levels[i] = getForestLevel(i);
        }
        return levels;
    }

    /**
     *  Creates the Levels for the volcano area.
     */
    Level[] getVolcanoLevels() {
        int LevelAmount = 3;
        Level[] levels = new Level[LevelAmount];
        for (int i = 0; i < LevelAmount; i++) {
            levels[i] = getVolcanoLevel(i);
        }
        return levels;
    }

}
