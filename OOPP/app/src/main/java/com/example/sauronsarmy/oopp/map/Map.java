package com.example.sauronsarmy.oopp.map;

import com.example.sauronsarmy.oopp.monsterPack.monsterFactory;
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

    public static Map getInstance() {
        return mapInstance;
    }


    private Map() {
        bgRef = R.drawable.mapbg;
        areas = createAreas();
        currentArea = areas[0];
        monfac = new monsterFactory();
    }

    public int damageMonster(int damage) {
        int ret = getCurrentArea().getCurrentLevel().damageMonster(damage);
        if (ret > 0) {
            getCurrentArea().checkComplete();
            return ret;
        }
        return 0;
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

    @Override
    public Area getCurrentArea(){
        return currentArea;
    }

    @Override
    public void setCurrentArea(Area area){
        currentArea=area;
    }

    @Override
    public Area[] getAreas(){
        return areas;
    }

    @Override
    public Level getCurrentLevel(){
        return getCurrentArea().getCurrentLevel();
    }

    @Override
    public Level createLevel(areaType areaType){
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
    @Override
    public int getLevelGoldMultiplier(int areaIndex, int levelIndex){
        Level level= areas[areaIndex].getLevels()[levelIndex];
        return level.getGoldMultiplier();
    }

    //Gets the HealthMultiplier for the given Level in the given Area.
    @Override
    public int getLevelHealthMultiplier(int areaIndex, int levelIndex){
        Level[] levels= areas[areaIndex].getLevels();
        return levels[levelIndex].getHealthMultiplier();
    }

    @Override
    public  int getLevelAmount(int areaIndex){
        return areas[areaIndex].getLevels().length;
    }

    @Override
    public  int getAreaAmount(){
        return areas.length;
    }

    @Override
    public  Area createArea(int areaIndex){
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

    @Override
    public  areaType getAreaType(int areaIndex) {
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

    @Override
    public  int getAreaBgRef(int areaIndex){
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


    public boolean nextLevel(){

        int lvlpos = getLevelIndex() +1;
        if(lvlpos >= getCurrentArea().getLevels().length){
            if(getAreaIndex()+1 < getAreas().length){

                return tryChangeAreaLevel(0,getAreaIndex()+1);
            }
            return false;

        }
        else{
            return tryChangeAreaLevel(lvlpos,getAreaIndex());
        }
    }

    public boolean previousLevel(){
        int lvlpos = getLevelIndex() -1;
        if(lvlpos <0){
            if(getAreaIndex()-1 >=0){
                return tryChangeAreaLevel(getArea(getAreaIndex()-1).getLevels().length -1 ,getAreaIndex()-1);
            }
            return false;

        }
        else{
            return tryChangeAreaLevel(lvlpos,getAreaIndex());
        }

    }

    private int getLevelIndex(){

        int pos =0;
        Level curLvl = getCurrentLevel();
        Level[] lvls = getCurrentArea().getLevels();

        for(int i =0; i< lvls.length ; i++){
            if(lvls[i].equals(curLvl)){
                pos =i;
                break;

            }
        }
        return pos;
    }


    private int getAreaIndex(){

        int pos =0;
        Area currentArea = getCurrentArea();
        Area[] areas = getAreas();

        for(int i =0; i< areas.length ; i++){
            if(areas[i].equals(currentArea)){
                pos =i;
                break;

            }
        }
        return pos;
    }

    @Override
    public boolean tryChangeAreaLevel(int level, int area) {

        if(area ==0){
            changeArea(area);
            if(level ==0){
                changeLvl(level);
                return true;
            }
            else {

                if(getCurrentArea().getLevel(level-1)!=null && (getCurrentArea().getLevel(level-1).getComplete())){
                    changeLvl(level);
                    return true;
                }

            }
        }
        else {

            if(getArea(area-1).getComplete() && getAreas().length>area){
                changeArea(area);
                if(level ==0){
                    changeLvl(level);
                    return true;
                }
                else {

                    if(getCurrentArea().getLevel(level-1)!=null && (getCurrentArea().getLevel(level-1).getComplete())){
                        changeLvl(level);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void changeLvl(int index) {

        getCurrentArea().setCurrentLevel(getCurrentArea().getLevels()[index]);
    }
    public void changeArea(int index){
        setCurrentArea(getArea(index));
        int imgref= getArea(index).getImgRef();
        setBackgroundRef(imgref);
    }


}
