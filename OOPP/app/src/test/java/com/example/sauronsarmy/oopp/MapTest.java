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
        for(int i=0;i<areas.length;i++){
            if(!(BuildConfig.DEBUG && map.getCurrentArea()==areas[i])){
                throw new AssertionError();
            }
        }
    }

    @Test
    public void testAreaTypes(){
        if(!(BuildConfig.DEBUG && areas[0].getAreaType()==areaType.MOUNTAIN)){
            throw new AssertionError();
        }
        if(!(BuildConfig.DEBUG && areas[1].getAreaType()==areaType.FOREST)) {
            throw new AssertionError();
        }
        if(!(BuildConfig.DEBUG && areas[2].getAreaType()==areaType.VOLCANO)) {
            throw new AssertionError();
        }
    }

}
