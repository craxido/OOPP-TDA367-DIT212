package com.example.sauronsarmy.oopp;

/**
 * @Author Jonatan Kallman
 */

//Imports
import com.example.sauronsarmy.oopp.map.MapPresenter;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

//This is a test file for the Map package and its files.
public class MapTest {

    private MapPresenter map;

    @Before
    public void setUp() throws Exception {
        map = new MapPresenter();
    }

    @After
    public void tearDown() throws Exception {
        map = null;
    }

    @Test
    public void exists() throws Exception {
        assertTrue(map != null);
    }

    @Test
    public void testGetCurrentArea() throws Exception {
        map.compareAreas(map.getCurrentArea(), map.createArea(0));
    }

    @Test
    public void testAreaTypes() throws Exception{

        for(int i = 0; i < map.getAreaAmount(); i++) {
            map.changeArea(i);
            areaType currentAreaType = map.getAreaType(i);

            switch (currentAreaType){
                case MOUNTAIN:
                    assertEquals(currentAreaType, areaType.MOUNTAIN);
                    break;
                case FOREST:
                    assertEquals(currentAreaType, areaType.FOREST);
                    break;
                case VOLCANO:
                    assertEquals(currentAreaType, areaType.VOLCANO);
                    break;
                default: throw new AssertionError();
            }

        }
    }

    @Test
    public void testImageRefs() throws Exception{
        for(int i=0; i < map.getAreaAmount(); i++){
            map.changeArea(i);
            int currentBgRef = map.getAreaBgRef(i);

            switch (map.getAreaType(i)) {
                case MOUNTAIN:
                    assertEquals(currentBgRef, R.drawable.mountainarea);
                    break;
                case FOREST:
                    assertEquals(currentBgRef, R.drawable.forestarea);
                    break;
                case VOLCANO:
                    assertEquals(currentBgRef, R.drawable.volcanoarea);
                    break;
            }
        }
    }

    @Test
    public void testGetMapBgRef() throws Exception{
        assertEquals(map.getMapBgRef(), R.drawable.mapbg);
    }

    @Test
    public void testMountainLevel() throws Exception{
        map.changeArea(0);
        map.changeLvl(0);
        assertTrue("This should be true: ", map.compareLevels(map.getCurrentLevel(), map.createMountainLvl(0)));
    }

    @Test
    public void testForestLevel() throws Exception{
        map.changeArea(1);
        map.changeLvl(0);
        assertTrue("This should be true: ", map.compareLevels(map.getCurrentLevel(), map.createForestLvl(0)));
    }

    @Test
    public void testVolcanoLevel() throws Exception{
        map.changeArea(2);
        map.changeLvl(0);
        assertTrue("This should be true: ", map.compareLevels(map.getCurrentLevel(), map.createVolcanoLvl(0)));
    }

    @Test
    public void testMountainGoldMultipliers(){
        for (int j = 0; j < map.getLevelAmount(0); j++){ //For every level in that area
            assertEquals(map.getLevelGoldMultiplier(0,j), 10*(j+1));
        }
    }

    @Test
    public void testForestGoldMultipliers(){
        for (int j = 0; j < map.getLevelAmount(1); j++){ //For every level in that area
            assertEquals(map.getLevelGoldMultiplier(1,j), 20*(j+1));
        }
    }

    @Test
    public void testVolcanoGoldMultipliers(){
        for (int j = 0; j < map.getLevelAmount(2); j++){ //For every level in that area
            assertEquals(map.getLevelGoldMultiplier(2,j), 30*(j+1));
        }
    }

    @Test
    public void testMountainHealthMultipliers(){
        for (int j = 0; j < map.getLevelAmount(0); j++){ //For every level in that area
            assertEquals(map.getLevelHealthMultiplier(0,j), 100*(j+1));
        }
    }

    @Test
    public void testForestHealthMultipliers(){
        for (int j = 0; j < map.getLevelAmount(1); j++){ //For every level in that area
            assertEquals(map.getLevelHealthMultiplier(1,j), 20000*(j+1));
        }
    }

    @Test
    public void testVolcanoHealthMultipliers(){
        for (int j = 0; j < map.getLevelAmount(2); j++){ //For every level in that area
            assertEquals(map.getLevelHealthMultiplier(2,j), 300000*(j+1));
        }
    }
}
