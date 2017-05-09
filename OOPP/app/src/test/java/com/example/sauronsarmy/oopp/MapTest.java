package com.example.sauronsarmy.oopp;

/**
 * Created by Jonatan on 2017-05-08.
 */

//Imports
import com.example.sauronsarmy.oopp.Map.Map;
import com.example.sauronsarmy.oopp.Map.areaType;
import com.example.sauronsarmy.oopp.Map.Area;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class MapTest {
    private Map map;
    private Area[] areas = map.getAreas();

    @Before
    public void setUp() throws Exception {
        map = Map.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        map = null;
    }

    @Test
    public void testCurrentArea() throws Exception {
        for(int i=0;i<areas.length;i++){
            if(!(BuildConfig.DEBUG && map.getCurrentArea()==areas[i])){
                throw new AssertionError();
            }
        }
    }

    @Test
    public void testAreaTypes() throws Exception{
        for(int i=0;i<areas.length;i++) {
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
        for(int i=0;i<areas.length;i++){
            assertEquals(areas[i].getImgRef(), R.drawable.forestarea
                    | R.drawable.mountainarea | R.drawable.volcanoarea);
        }
    }

    @Test
    public void testGetMapBgRef() throws Exception{
        assertEquals(map.getBackgroundRef(),R.drawable.mapbg);
    }

}
