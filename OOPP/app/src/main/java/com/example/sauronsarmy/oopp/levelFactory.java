package com.example.sauronsarmy.oopp;

/**
 * Created by Jonatan on 2017-04-25.
 */

class levelFactory {
    public levelFactory(){}

    monsterFactory monfac = new monsterFactory();

    private Level getLevel(areaType type) {

        // Level constructor:
        // Level(Monster monster, double healthMultiplier, double goldMultiplier, areaType area)
        // I just added some random values for health/ gold/ multipliers for now.
        switch (type) {
            case FOREST:
                return new Level(monfac.getMonster(100, 100, type), 1, 1, type);
            case MOUNTAIN:
                return new Level(monfac.getMonster(200, 200, type), 1, 1, type);
            case VOLCANO:
                return new Level(monfac.getMonster(300, 300, type), 1, 1, type);
            default:
                return null;
        }

    }

    public Level[] getLevels(areaType type) {

        // LevelAmount could be set depending on area.
        int LevelAmount=10;
        Level[] levels = new Level[LevelAmount];

        // Uses getLevel().
        // Right now, this only creates one level for each area.
        levels[0]=getLevel(type);


        return levels;

    }
}