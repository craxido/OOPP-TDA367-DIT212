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
        map.setCurrentArea(areas[0]);
        if(BuildConfig.DEBUG && map.getCurrentArea()==areas[0]){
            throw new AssertionError();
        }
        map.setCurrentArea(areas[1]);
        if(BuildConfig.DEBUG && map.getCurrentArea()==areas[1]){
            throw new AssertionError();
        }
        map.setCurrentArea(areas[2]);
        if(BuildConfig.DEBUG && map.getCurrentArea()==areas[2]){
            throw new AssertionError();
        }
    }

    @Test
    public void testAreaTypes(){
        if(BuildConfig.DEBUG && areas[0].getAreaType()==areaType.MOUNTAIN){
            throw new AssertionError();
        }
        if(BuildConfig.DEBUG && areas[1].getAreaType()==areaType.FOREST) {
            throw new AssertionError();
        }
        if(BuildConfig.DEBUG && areas[2].getAreaType()==areaType.VOLCANO) {
            throw new AssertionError();
        }
    }

}
