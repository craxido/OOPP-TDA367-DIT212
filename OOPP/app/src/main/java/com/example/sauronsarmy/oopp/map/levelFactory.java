package com.example.sauronsarmy.oopp.map;

import com.example.sauronsarmy.oopp.areaType;
import com.example.sauronsarmy.oopp.monsterPack.monsterFactory;

/**
 * @author Jonatan Källman
 */

class levelFactory {
    levelFactory(){}

    private monsterFactory monfac = new monsterFactory();
    Level[] getForestLevels(){
        int LevelAmount=10;
        Level[] levels = new Level[10];
        for(int i=0; i < LevelAmount; i++){
            levels[i]=new Level(monfac.getForestMonster(20000*(i+1), 500), 20000*(i+1) , 20*(i+1), areaType.FOREST, i);
        }
        return levels;
    }

    Level[] getMountainLevels(){
        int LevelAmount=10;
        Level[] levels = new Level[10];
        for(int i=0; i < LevelAmount; i++){
            levels[i]=new Level(monfac.getMountainMonster(100*(i+1), 100), 100*(i+1), 10*(i+1), areaType.MOUNTAIN, i);
        }
        return levels;
    }

    Level[] getVolcanoLevels(){
        int LevelAmount=10;
        Level[] levels = new Level[10];
        for(int i=0; i < LevelAmount; i++){
            levels[i]=new Level(monfac.getVolcanoMonster(300000*(i+1), 3000), 300000*(i+1), 30*(i+1), areaType.VOLCANO, i);
        }
        return levels;
    }
/*
    Level getLevel(areaType type, int scale) {

        // Level constructor:
        // Level(Monster monster, double healthMultiplier, double goldMultiplier, areaType area)
        // I just added some random values for health/ gold/ multipliers for now.
        switch (type) {

            case FOREST:
                return new Level(monfac.getForestMonster(20000*scale, 500),20000*scale , 20*scale, type);
            case MOUNTAIN:
                return new Level(monfac.getMountainMonster(100*scale, 100), 100*scale, 10*scale, type);
            case VOLCANO:
                return new Level(monfac.getVolcanoMonster(300000*scale, 3000), 300000*scale, 30*scale, type);
            default:
                return null;
        }
    }
*/
}
