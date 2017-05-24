package com.example.sauronsarmy.oopp.map;

import com.example.sauronsarmy.oopp.areaType;
import com.example.sauronsarmy.oopp.monsterPack.monsterFactory;

/**
 * @author Jonatan Källman
 */

class levelFactory {
    levelFactory(){}

    private monsterFactory monfac = new monsterFactory();
    private Level getLevel(areaType type, int scale) {

        // Level constructor:
        // Level(Monster monster, double healthMultiplier, double goldMultiplier, areaType area)
        // I just added some random values for health/ gold/ multipliers for now.
        switch (type) {

            case FOREST:
                return new Level(monfac.getMonster(20000*scale, 500, type),20000*scale , 20*scale, type);
            case MOUNTAIN:
                return new Level(monfac.getMonster(100*scale, 100, type), 100*scale, 1*scale, type);
            case VOLCANO:
                return new Level(monfac.getMonster(300000*scale, 3000, type), 300000*scale, 30*scale, type);
            default:
                return null;
        }

    }

    Level[] getLevels(areaType type) {
        // LevelAmount could be set depending on area.
        int LevelAmount=3;
        Level[] levels = new Level[LevelAmount];
        for(int i=0; i < LevelAmount; i++){
            levels[i]=getLevel(type,i+1);
        }
        // Uses getLevel().
        // Right now, this only creates one level for each area.
        return levels;
    }
}
