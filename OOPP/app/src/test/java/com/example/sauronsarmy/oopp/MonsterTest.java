package com.example.sauronsarmy.oopp;

/**
 * Created by Filip on 2017-04-29.
 */

        import com.example.sauronsarmy.oopp.monsterPack.Monster;

        import org.junit.Test;
        import org.junit.Before;
        import org.junit.After;

public class MonsterTest {

    private Monster monster,monster2;

    @Before
    public void setUp() throws  Exception{
        monster = new Monster(10,10,R.drawable.mike, false);
        monster2 = new Monster(10,10,R.drawable.mike,false);


    }

    @After
    public void tearDown() throws Exception{
        monster = null;
    }

    //Tests the damage monster function
    @Test
    public void damageMonsterTest() throws Exception{
        monster.damageMonster(5);
        assert (monster.getHealth() == 5);
        assert (monster.damageMonster(5));
    }

    //Tests the get IMGRef function
    @Test
    public void getIMGRefTest()throws Exception{

        assert (monster.getImageRef() == R.drawable.mike);

    }

    @Test
    public void testEquals(){
        assert (monster.equals(monster2));
    }

    @Test
    public void isBossTest(){

        assert (monster.isBoss() ==false);
    }

    @Test
    public void getHealthTest(){

        assert (monster.getHealth() == 10);

    }

    @Test
    public void setHealthTest(){
        monster.setHealth(15);
        assert (monster.getHealth() ==15);

    }

    @Test
    public void getGold(){

        assert (monster.getGold()==10);
    }

    @Test
    public void getMaxHealth(){

        assert (monster.getMaxHealth() == 10);
    }
}
