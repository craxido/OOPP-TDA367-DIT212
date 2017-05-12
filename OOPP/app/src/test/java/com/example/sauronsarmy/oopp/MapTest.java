package com.example.sauronsarmy.oopp;

/**
 * @Author Jonatan Kallman
 */

//Imports
import com.example.sauronsarmy.oopp.MonsterPack.monsterFactory;
import com.example.sauronsarmy.oopp.Map.Level;
import com.example.sauronsarmy.oopp.Map.Map;
import com.example.sauronsarmy.oopp.Map.areaType;
import com.example.sauronsarmy.oopp.Map.Area;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

//This is a test file for the Map package and its files.
public class MapTest {
    private Map map;
    private Area[] areas = map.getAreas();
    private monsterFactory monfac = new monsterFactory();

    @Before
    public void setUp() throws Exception {
        map = Map.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        map = null;
    }

    @Test
    public void testGetCurrentArea() throws Exception {
        for(int i = 0; i<areas.length; i++){
            if(!(BuildConfig.DEBUG && map.getCurrentArea() == areas[i])){
                throw new AssertionError();
            }
        }
    }

    @Test
    public void testAreaTypes() throws Exception{
        for(int i = 0; i<areas.length; i++) {
            switch (areas[i].getAreaType()){
                case FOREST:
                    assertEquals(areas[i].getAreaType(), areaType.FOREST);
                    break;
                case MOUNTAIN:
                    assertEquals(areas[i].getAreaType(), areaType.MOUNTAIN);
                    break;
                case VOLCANO:
                    assertEquals(areas[i].getAreaType(), areaType.VOLCANO);
                    break;
                default: throw new AssertionError();
            }
        }
    }

    @Test
    public void testImageRef() throws Exception{
        for(int i=0; i<areas.length; i++){
            assertEquals(areas[i].getImgRef(), R.drawable.forestarea
                    | R.drawable.mountainarea | R.drawable.volcanoarea);
        }
    }

    @Test
    public void testGetMapBgRef() throws Exception{
        assertEquals(map.getBackgroundRef(),R.drawable.mapbg);
    }

    @Test
    public void testCurrentLevel() throws Exception { //Will have to be updated as more levels are added.
        for (int i = 0; i < areas.length; i++) {
            switch (areas[i].getAreaType()) {
                case FOREST:
                    assertEquals(areas[i].getCurrentLevel(), new Level(monfac.getMonster(100, 100, areaType.FOREST), 1, 1, areaType.FOREST));
                    break;
                case MOUNTAIN:
                    assertEquals(areas[i].getCurrentLevel(), new Level(monfac.getMonster(200, 200, areaType.MOUNTAIN), 2, 2, areaType.MOUNTAIN));
                    break;
                case VOLCANO:
                    assertEquals(areas[i].getCurrentLevel(), new Level(monfac.getMonster(300, 300, areaType.VOLCANO), 3, 3, areaType.VOLCANO));
                    break;
                default: //Not a valid area
                    throw new AssertionError();
            }
        }
    }

    @Test
    public void testGoldMultiplier(){
        for (int i = 0; i < areas.length; i++) { //For every area
            Level[] levels = areas[i].getLevels(); //Get the levels

            switch (areas[i].getAreaType()) { //Check the area type
                case FOREST:
                    for (int j = 0; i < levels.length; j++) { //For every level in that area
                        assertEquals(levels[j].getGoldMultiplier(), 1);
                    }
                    break;

                case MOUNTAIN:
                    for (int j = 0; i < levels.length; j++){ //For every level in that area
                        assertEquals(levels[j].getGoldMultiplier(),2);
                    }
                    break;

                case VOLCANO:
                    for (int j = 0; i < levels.length; j++){ //For every level in that area
                        assertEquals(levels[j].getGoldMultiplier(),3);
                    }
                    break;

                default: //Not a valid area
                    throw new AssertionError();
            }
        }
    }

    @Test
    public void testHealthMultiplier(){
        for (int i = 0; i < areas.length; i++) { //For every area

            Level[] levels = areas[i].getLevels();
            switch (areas[i].getAreaType()) { //Check the area type
                case FOREST:
                    for (int j = 0; i < levels.length; j++) { //For every level in that area
                        assertEquals(levels[j].getHealthMultiplier(), 1);
                    }
                    break;

                case MOUNTAIN:
                    for (int j = 0; i < levels.length; j++){ //For every level in that area
                        assertEquals(levels[j].getHealthMultiplier(),2);
                    }
                    break;

                case VOLCANO:
                    for (int j = 0; i < levels.length; j++){ //For every level in that area
                        assertEquals(levels[j].getHealthMultiplier(),3);
                    }
                    break;

                default: //Not a valid area
                    throw new AssertionError();
            }
        }
    }
}
