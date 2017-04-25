package com.example.sauronsarmy.oopp;

/**
 * Created by Jonatan on 2017-04-25.
 */

class levelFactory {
    public levelFactory(){}
    monsterFactory monfac = new monsterFactory();

    public Level getLevel(areaType type){

        // Level constructor:
        // Level(Monster monster, double healthMultiplier, double goldMultiplier, areaType area)
        // I just added some random values for health/ gold/ multipliers for now.
        switch(type){
            case FOREST:return new Level(monfac.getMonster(100, 100, type), 1, 1, type);
            case MOUNTAIN:return new Level(monfac.getMonster(200, 200, type), 1, 1, type);
            case VOLCANO:return new Level(monfac.getMonster(300, 300, type), 1, 1, type);
            default: return null;
        }

    }
}
