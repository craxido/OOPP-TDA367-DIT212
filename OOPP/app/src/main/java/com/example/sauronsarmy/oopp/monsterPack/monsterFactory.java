package com.example.sauronsarmy.oopp.monsterPack;


import com.example.sauronsarmy.oopp.areaType;
import com.example.sauronsarmy.oopp.R;

import java.util.Random;

/**
 * Created by Filip on 2017-04-05.
 */

public class monsterFactory {
    public monsterFactory(){}

    /* length of the int arrays for monster images.
      The length should always be equal i.e. each area should equal amount of monster images.
      */
    private static final int MAX = 2;

    private int[] forestMonsters = {
            R.drawable.forest_monster_01,
            R.drawable.forest_monster_11};

    private int[] mountainMonsters = {
            R.drawable.mountain_monster_01,
            R.drawable.mountain_monster_11
    };

    private int[] volcanoMonsters={
            R.drawable.volcano_monster_01,
            R.drawable.volcano_monster_11
    };
    private Random rand = new Random();
    private int bossMonsterImg = R.drawable.boss_monster;

    public IMonster getMonster(int hp, int gold, areaType type){

        switch (type){

            case FOREST:
                return new Monster(hp, gold, forestMonsters[rand.nextInt(MAX)], false);
            case MOUNTAIN:
                return new Monster(hp, gold, mountainMonsters[rand.nextInt(MAX)], false);
            case VOLCANO:
                return new Monster(hp, gold, volcanoMonsters[rand.nextInt(MAX)], false);
            default:
                return new Monster(hp, gold, R.drawable.mike, false );

        }
    }


    public IMonster getMountainMonster(int hp, int gold){
        return new Monster(hp, gold, mountainMonsters[0], false);
    }

    public IMonster getForestMonster(int hp, int gold){
        return new Monster(hp, gold, forestMonsters[0], false);
    }

    public IMonster getVolcanoMonster(int hp, int gold){
        return new Monster(hp, gold, volcanoMonsters[0], false);
    }

    public IMonster getBossMonster(int hp, int gold){
        return new BossMonster(hp, gold, bossMonsterImg);
    }

}
