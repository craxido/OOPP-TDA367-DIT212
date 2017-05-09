package com.example.sauronsarmy.oopp.MonsterPack;


import com.example.sauronsarmy.oopp.Map.areaType;
import com.example.sauronsarmy.oopp.R;

/**
 * Created by Filip on 2017-04-05.
 */

public class monsterFactory {
    public monsterFactory(){}

    public Monster getMonster(int hp, int gold, areaType type){


        switch (type){

            case FOREST:
                return new Monster(hp, gold, R.drawable.mike );
            case MOUNTAIN:
                return new Monster(hp, gold,R.drawable.mike);
            case VOLCANO:
                return new Monster(hp, gold ,R.drawable.mike);
            default:
                return new Monster(hp, gold,R.drawable.mike );

        }


    }
}
