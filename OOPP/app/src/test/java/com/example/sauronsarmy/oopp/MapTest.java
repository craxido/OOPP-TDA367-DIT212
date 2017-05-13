package com.example.sauronsarmy.oopp;

/**
 * @Author Jonatan Kallman
 */

//Imports
import com.example.sauronsarmy.oopp.Map.MapPresenter;
import com.example.sauronsarmy.oopp.MonsterPack.monsterFactory;
import com.example.sauronsarmy.oopp.Map.areaType;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

//This is a test file for the Map package and its files.
public class MapTest {
    private MapPresenter map;
    //private Area[] areas = map.getAreas();
    private monsterFactory monfac = new monsterFactory();

    @Before
    public void setUp() throws Exception {
        map = MapPresenter.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        map = null;
    }

    @Test
    public void testGetCurrentArea() throws Exception {
        for(int i = 0; i < map.getAreaAmount(); i++){
            if(!(BuildConfig.DEBUG && map.getCurrentArea() == map.createArea(i))){
                throw new AssertionError();
            }
        }
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
            assertEquals(map.getAreaBgRef(i), R.drawable.forestarea
                    | R.drawable.mountainarea | R.drawable.volcanoarea);
        }
    }

    @Test
    public void testGetMapBgRef() throws Exception{
        assertEquals(map.getBackgroundRef(),R.drawable.mapbg);
    }

    @Test
    public void testCurrentLevel() throws Exception { //Will have to be updated as more levels are added.
        for (int i = 0; i < map.getAreaAmount(); i++) {
            switch (map.getAreaType(i)) {
                case FOREST:
                    assertEquals(map.getCurrentLevel(), map.makeLevel(areaType.FOREST));
                    break;
                case MOUNTAIN:
                    assertEquals(map.getCurrentLevel(), map.makeLevel(areaType.MOUNTAIN));
                    break;
                case VOLCANO:
                    assertEquals(map.getCurrentLevel(), map.makeLevel(areaType.VOLCANO));
                    break;
                default: //Not a valid area
                    throw new AssertionError();
            }
        }
    }

    @Test
    public void testGoldMultiplier(){
        for (int i = 0; i < map.getAreaAmount(); i++) { //For every area
            switch (map.getAreaType(i)) { //Check the area type
                //NOTICE: "j" is the index of the level, "i" is the index of the area.
                case FOREST:
                    for (int j = 0; i < map.getLevelAmount(i); j++) { //For every level in that area
                        assertEquals(map.getLevelGoldMultiplier(i,j),1);
                    }
                    break;

                case MOUNTAIN:
                    for (int j = 0; i < map.getLevelAmount(i); j++){ //For every level in that area
                        assertEquals(map.getLevelGoldMultiplier(i,j),2);
                    }
                    break;

                case VOLCANO:
                    for (int j = 0; i < map.getLevelAmount(i); j++){ //For every level in that area
                        assertEquals(map.getLevelGoldMultiplier(i,j),3);
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
                case FOREST:
                    for (int j = 0; i < map.getLevelAmount(i); j++) { //For every level in that area
                        assertEquals(map.getLevelHealthMultiplier(i,j), 1);
                    }
                    break;

                case MOUNTAIN:
                    for (int j = 0; i < map.getLevelAmount(i); j++){ //For every level in that area
                        assertEquals(map.getLevelHealthMultiplier(i,j), 2);
                    }
                    break;

                case VOLCANO:
                    for (int j = 0; i < map.getLevelAmount(i); j++){ //For every level in that area
                        assertEquals(map.getLevelHealthMultiplier(i,j), 3);
                    }
                    break;

                default: //Not a valid area
                    throw new AssertionError();
            }
        }
    }
}
