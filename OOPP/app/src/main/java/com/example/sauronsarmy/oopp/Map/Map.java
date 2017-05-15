package com.example.sauronsarmy.oopp.Map;

import com.example.sauronsarmy.oopp.Player.PlayerModel;
import com.example.sauronsarmy.oopp.Player.PlayerModelInterface;

import com.example.sauronsarmy.oopp.MonsterPack.monsterFactory;

import com.example.sauronsarmy.oopp.R;

/**
 * @author Jonatan Källman
 */

class Map implements MapMVPInterface.ModelOps {

    private static Area[] areas;
    private Area currentArea;
    private static final Map mapInstance = new Map();
    private static levelFactory lvlfac;
    private monsterFactory monfac;
    private int bgRef;

    private static PlayerModelInterface player = PlayerModel.getInstance();

    public static Map getInstance() {
        return mapInstance;
    }


    private Map() {
        bgRef = R.drawable.mapbg;
        areas = createAreas();
	      currentArea = areas[0];
        monfac = new monsterFactory();
    }

    //Creates areas for the mapInstance
    private static Area[] createAreas(){
        Area[] areas= new Area[3];
        lvlfac = new levelFactory();
        //Area 1 (Mountain)
        areas[0] = new Area(R.drawable.mountainarea, areaType.MOUNTAIN, lvlfac.getLevels(areaType.MOUNTAIN), 0);
        //Area 2 (Forest)
        areas[1] = new Area(R.drawable.forestarea, areaType.FOREST, lvlfac.getLevels(areaType.FOREST), 1);
        //Area 3 (Volcano)
        areas[2] = new Area(R.drawable.volcanoarea, areaType.VOLCANO, lvlfac.getLevels(areaType.VOLCANO), 2);

        return areas;
    }

    @Override
    public int getBackgroundRef() {
        return this.bgRef;
    }

    @Override
    public void setBackgroundRef(int bgRef) {
        this.bgRef = bgRef;
    }

    @Override
    public Area getArea(int index) {
        return areas[index];
    }

    @Override
    public void setArea(Area area, int index) {
        areas[index] = area;
    }

    @Override
    public void onDestroy() {}

    Area getCurrentArea(){
        return currentArea;
    }

    void setCurrentArea(Area area){
        currentArea=area;
    }

    Area[] getAreas(){
        return areas;
    }


    public int getPlayerMoney(){
        return player.getMoney();
    }

    Level createLevel(areaType areaType){
        switch (areaType){
            case MOUNTAIN:
                return new Level(monfac.getMonster(100, 100, areaType), 1, 1, areaType);
            case FOREST:
                return new Level(monfac.getMonster(200, 200, areaType), 2, 2, areaType);
            case VOLCANO:
                return new Level(monfac.getMonster(300, 300, areaType), 3, 3, areaType);
            default:
                return null; //Can this be handled better?
        }
    }

    //Gets the GoldMultiplier for the given Level in the given Area.
    int getLevelGoldMultiplier(int areaIndex, int levelIndex){
        Level level= areas[areaIndex].getLevels()[levelIndex];
        return level.getGoldMultiplier();
    }

    //Gets the HealthMultiplier for the given Level in the given Area.
    int getLevelHealthMultiplier(int areaIndex, int levelIndex){
        Level[] levels= areas[areaIndex].getLevels();
        return levels[levelIndex].getHealthMultiplier();
    }

    int getLevelAmount(int areaIndex){
        return areas[areaIndex].getLevels().length;
    }

    int getAreaAmount(){
        return areas.length;
    }

    Area createArea(int areaIndex){
        switch (areaIndex){
            case 0:
                return new Area(R.drawable.mountainarea, areaType.MOUNTAIN, lvlfac.getLevels(areaType.MOUNTAIN), 0);
            case 1:
                return new Area(R.drawable.forestarea, areaType.FOREST, lvlfac.getLevels(areaType.FOREST), 1);
            case 2:
                return new Area(R.drawable.volcanoarea, areaType.VOLCANO, lvlfac.getLevels(areaType.VOLCANO), 2);
            default: //Not a valid (or yet listed) area.
                 return null;
        }
    }

    areaType getAreaType(int areaIndex) {
        switch (areaIndex) {
            case 0:
                return areaType.MOUNTAIN;
            case 1:
                return areaType.FOREST;
            case 2:
                return areaType.VOLCANO;
            default: //Not a valid (or yet listed) area.
                return null;
        }
    }

    int getAreaBgRef(int areaIndex){
        switch (areaIndex) {
            case 0:
                return R.drawable.mountainarea;
            case 1:
                return R.drawable.forestarea;
            case 2:
                return R.drawable.volcanoarea;
            default: //Not a valid (or yet listed) area.
                return -1;
        }
    }

    Level getCurrentLevel(){
        return getCurrentArea().getCurrentLevel();
    }

}
