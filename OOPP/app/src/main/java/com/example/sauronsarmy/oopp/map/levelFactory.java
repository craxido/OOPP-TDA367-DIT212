package com.example.sauronsarmy.oopp.map;

import com.example.sauronsarmy.oopp.areaType;
import com.example.sauronsarmy.oopp.monsterPack.monsterFactory;

/**
 * @author Jonatan Källman
 */

class levelFactory {
    levelFactory(){}

    private monsterFactory monfac = new monsterFactory();
    private Level getLevel(areaType type) {

        // Level constructor:
        // Level(Monster monster, double healthMultiplier, double goldMultiplier, areaType area)
        // I just added some random values for health/ gold/ multipliers for now.
        switch (type) {

            case FOREST:
                return new Level(monfac.getMonster(200, 200, type), 2, 2, type);
            case MOUNTAIN:
                return new Level(monfac.getMonster(100, 100, type), 1, 1, type);
            case VOLCANO:
                return new Level(monfac.getMonster(300, 300, type), 3, 3, type);
            default:
                return null;
        }

    }

    Level[] getLevels(areaType type) {
        // LevelAmount could be set depending on area.
        int LevelAmount=1;
        Level[] levels = new Level[LevelAmount];
        for(int i=0; i < LevelAmount; i++){
            levels[i]=getLevel(type);
        }
        // Uses getLevel().
        // Right now, this only creates one level for each area.
        return levels;
    }
}
