package com.example.sauronsarmy.oopp.map;

import com.example.sauronsarmy.oopp.areaType;
import com.example.sauronsarmy.oopp.monsterPack.monsterFactory;
import com.example.sauronsarmy.oopp.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * @author Jonatan Källman
 */

class Map implements MapMVPInterface.ModelOps {

    private static final String TAG = "Map";
    private static SharedPreferences saveState;
    private static Area[] areas;
    private Area currentArea;
    private static Map mapInstance;
    private static levelFactory lvlfac;
    private monsterFactory monfac;
    private int bgRef;
    private int completedGoals;

    //Overloading so that context doesn't have to be sent once map is created
    public static MapMVPInterface.ModelOps getInstance() {
        if (mapInstance == null){
            mapInstance = new Map();
        }
        return mapInstance;
    }

    //If there is not map yet, create one. Else, get the instance.
    public static MapMVPInterface.ModelOps getInstance(Context context) {
        if (mapInstance == null)
            mapInstance = new Map(context.getApplicationContext());
        return mapInstance;
    }

    //Overload
    private Map () {
        bgRef = R.drawable.mapbg;
        areas = createAreas();
        currentArea = areas[0];
        monfac = new monsterFactory();
    }

    private Map (Context context) {
        bgRef = R.drawable.mapbg;
        areas = createAreas();
        currentArea = areas[0];
        monfac = new monsterFactory();
        loadState(context.getApplicationContext()); //Load the map progress/ state
    }

    public void saveState(Context context) {
        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveState.edit();
        editor.putInt("bgRef", getBackgroundRef());
        editor.putInt("currentArea", currentArea.getAreaIndex());
        editor.putInt("currentLevel", currentArea.getCurrentLevel().getLevelIndex());
        editor.putInt("completedGoals", completedGoals);
        editor.apply();
        Log.i(TAG, "Map state saved.");
    }

    public void loadState(Context context) {
        saveState = context.getSharedPreferences(context.getString(R.string.stateIdentifier),
                Context.MODE_PRIVATE);

        setBackgroundRef(saveState.getInt("bgRef", 0));
        setCurrentArea(areas[saveState.getInt("currentArea", 0)]);
        setCurrentLevel(getCurrentArea().getLevels()[saveState.getInt("currentLevel", 0)]);
        this.completedGoals = saveState.getInt("completedGoals", 0);
        setCompletedGoals(completedGoals);
        Log.i(TAG, "Map state loaded.");
    }

    private void setCompletedGoals(int completed){
        for (Area a : areas){
            if (!(completed == 0)){
                for(int i = 0; i < (completed % 10); i++){
                    a.completeLevel(i);
                    completed--;
                }
            }
        }
    }

    public int damageMonster(int damage) {
        int ret = getCurrentArea().getCurrentLevel().damageMonster(damage);
        if (ret > 0) {
            getCurrentArea().checkComplete();
            if(getCurrentLevel().getComplete() && !(getCurrentLevel().isChecked())){
                completedGoals++;
                Log.i(TAG, "Goals: " + completedGoals);
                getCurrentArea().getCurrentLevel().setChecked(true);
            }
            return ret;
        }
        return 0;
    }

    //Creates areas for the mapInstance
    private static Area[] createAreas(){
        Area[] areas= new Area[3];
        lvlfac = new levelFactory();
        //Area 1 (Mountain)
        areas[0] = new Area(R.drawable.mountainarea, areaType.MOUNTAIN, lvlfac.getMountainLevels(), 0);
        //Area 2 (Forest)
        areas[1] = new Area(R.drawable.forestarea, areaType.FOREST, lvlfac.getForestLevels(), 1);
        //Area 3 (Volcano)
        areas[2] = new Area(R.drawable.volcanoarea, areaType.VOLCANO, lvlfac.getVolcanoLevels(), 2);

        return areas;
    }

    private void setCurrentLevel(Level level){
        currentArea.setCurrentLevel(level);
    }

    @Override
    public int getBackgroundRef() {
        return this.bgRef;
    }

    @Override
    public int getMapBgRef() {
        return R.drawable.mapbg;
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
    public  areaType getAreaType(int areaIndex) {
        switch (areaIndex) {
            case 0:
                return areaType.MOUNTAIN;
            case 1:
                return areaType.FOREST;
            case 2:
                return areaType.VOLCANO;
            default: //Not a valid (or yet listed) area.
                Log.e(TAG, "ERROR: getAreaType(int areaIndex) was called," +
                        " but no valid areaIndex was given.");
                throw new IllegalArgumentException("No valid areaIndex found");
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
                Log.e(TAG, "ERROR: getAreaBgRef(int areaIndex) was called," +
                        " but no valid areaIndex was given.");
                throw new IllegalArgumentException("No valid areaIndex found");
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

       return currentArea.getAreaIndex();
    }

    //Check to see if allowed to change to given area[level], if you can , change, otherwise do nothing
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
        Level newLevel = getCurrentArea().getLevels()[index];
        getCurrentArea().setCurrentLevel(newLevel);
    }

    public void changeArea(int index){
        setCurrentArea(getArea(index));
        int imgref= getArea(index).getImgRef();
        setBackgroundRef(imgref);
    }

    @Override
    public  Area createArea(int areaIndex){
        switch (areaIndex){
            case 0:
                return new Area(R.drawable.mountainarea, areaType.MOUNTAIN, lvlfac.getMountainLevels(), areaIndex);
            case 1:
                return new Area(R.drawable.forestarea, areaType.FOREST, lvlfac.getForestLevels(), areaIndex);
            case 2:
                return new Area(R.drawable.volcanoarea, areaType.VOLCANO, lvlfac.getVolcanoLevels(), areaIndex);
            default: //Not a valid (or yet listed) area.
                Log.e(TAG, "ERROR: createArea(int areaIndex) was called," +
                        " but no valid areaIndex was given.");
                throw new IllegalArgumentException("No valid areaIndex found");
        }
    }

    //Functions accessible for tests (must be reached via MapPresenter).
    public Level createMountainLvl(int levelIndex){
        return lvlfac.getMountainLevel(levelIndex);
    }

    public Level createForestLvl(int levelIndex){
        return lvlfac.getForestLevel(levelIndex);
    }

    public Level createVolcanoLvl(int levelIndex){
        return lvlfac.getVolcanoLevel(levelIndex);
    }

}
