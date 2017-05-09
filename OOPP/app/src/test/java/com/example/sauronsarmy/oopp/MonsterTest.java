package com.example.sauronsarmy.oopp;

/**
 * Created by Filip on 2017-04-29.
 */

        import com.example.sauronsarmy.oopp.MonsterPack.Monster;

        import org.junit.Test;
        import org.junit.Before;
        import org.junit.After;

public class MonsterTest {

    private Monster monster;

    @Before
    public void setUp() throws  Exception{
        monster = new Monster(10,10,R.drawable.mike);

    }

    @After
    public void tearDown() throws Exception{
        monster = null;
    }

    //Tests the damage monster function
    @Test
    public void damageMonsterTest() throws Exception{
        monster.damageMonster(5);
        assert (monster.getHealth() ==5);
        assert (monster.damageMonster(5));
    }

    //Tests the get IMGRef function
    @Test
    public void getIMGRefTest()throws Exception{

        assert (monster.getImageRef() == R.drawable.mike);

    }
}
