package com.example.sauronsarmy.oopp;

/**
 * Created by Jonatan on 2017-05-08.
 */

//Imports
import com.example.sauronsarmy.oopp.Map.Map;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;


public class MapTest {
    private Map map;

    @Before
    public void setUp() throws Exception {
        map = Map.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        map = null;
    }


}
