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
            switch (map.getAreaType(i)){
                case FOREST:
                    assertEquals(map.getAreaType(i), areaType.FOREST);
                    break;
                case MOUNTAIN:
                    assertEquals(map.getAreaType(i), areaType.MOUNTAIN);
                    break;
                case VOLCANO:
                    assertEquals(map.getAreaType(i), areaType.VOLCANO);
                    break;
                default: throw new AssertionError();
            }
        }
    }

    @Test
    public void testImageRef() throws Exception{
        for(int i=0; i < map.getAreaAmount(); i++){
            switch (map.getAreaType(i)) {
                case MOUNTAIN:
                    assertEquals(map.getAreaBgRef(i), R.drawable.mountainarea);
                    break;
                case FOREST:
                    assertEquals(map.getAreaBgRef(i), R.drawable.forestarea);
                    break;
                case VOLCANO:
                    assertEquals(map.getAreaBgRef(i), R.drawable.volcanoarea);
                    break;
            }
        }
    }

    @Test
    public void testGetMapBgRef() throws Exception{
        assertEquals(map.getMapBgRef(), R.drawable.mapbg);
    }

    @Test
    public void testCurrentLevel() throws Exception { //Will have to be updated as more levels are added.
        for (int i = 0; i < map.getAreaAmount(); i++) {
            map.changeArea(i);
            switch (map.getAreaType(i)) {
                case MOUNTAIN:
                    assertTrue(map.compareLevels(map.getCurrentLevel(), map.createLevel(areaType.MOUNTAIN)));
                    break;
                case FOREST:
                    assertTrue(map.compareLevels(map.getCurrentLevel(), map.createLevel(areaType.FOREST)));
                    break;
                case VOLCANO:
                    assertTrue(map.compareLevels(map.getCurrentLevel(), map.createLevel(areaType.VOLCANO)));
                    break;
                default: //Not a valid area
                    throw new AssertionError();
            }
            break;
        }
    }

    //Trying to test only one area/ level at first
    @Test
    public void testCurrentLevelM() throws Exception{
        map.changeArea(0);
        map.changeLvl(0);
        assertTrue("This should be true: ", map.compareLevels(map.getCurrentLevel(), map.createLevelM()));
    }

    @Test
    public void testGoldMultiplier(){
        for (int i = 0; i < map.getAreaAmount(); i++) { //For every area
            switch (map.getAreaType(i)) { //Check the area type
                //NOTICE: "j" is the index of the level, "i" is the index of the area.

                case MOUNTAIN:
                    for (int j = 0; j < map.getLevelAmount(i); j++){ //For every level in that area
                        assertEquals(map.getLevelGoldMultiplier(i,j), 10*(j+1));
                    }
                    break;

                case FOREST:
                    for (int j = 0; j < map.getLevelAmount(i); j++) { //For every level in that area
                        assertEquals(map.getLevelGoldMultiplier(i,j), 20*(j+1));
                    }
                    break;

                case VOLCANO:
                    for (int j = 0; j < map.getLevelAmount(i); j++){ //For every level in that area
                        assertEquals(map.getLevelGoldMultiplier(i,j), 30*(j+1));
                    }
                    break;

                default: //Not a valid area
                    throw new AssertionError();
            }
        }
    }

    @Test
    public void testHealthMultiplier(){
        for (int i = 0; i < map.getAreaAmount(); i++) { //For every area
            switch (map.getAreaType(i)) { //Check the area type
                //NOTICE: "j" is the index of the level, "i" is the index of the area.

                case MOUNTAIN:
                    for (int j = 0; j < map.getLevelAmount(i); j++){ //For every level in that area
                        assertEquals(map.getLevelHealthMultiplier(i,j), 100*(j+1));
                    }
                    break;

                case FOREST:
                    for (int j = 0; j < map.getLevelAmount(i); j++) { //For every level in that area
                        assertEquals(map.getLevelHealthMultiplier(i,j), 20000*(j+1));
                    }
                    break;

                case VOLCANO:
                    for (int j = 0; j < map.getLevelAmount(i); j++){ //For every level in that area
                        assertEquals(map.getLevelHealthMultiplier(i,j), 300000*(j+1));
                    }
                    break;

                default: //Not a valid area
                    throw new AssertionError();
            }
        }
    }
}
