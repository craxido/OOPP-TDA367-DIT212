package com.example.sauronsarmy.oopp.map;

import com.example.sauronsarmy.oopp.areaType;
import com.example.sauronsarmy.oopp.monsterPack.monsterFactory;
import com.example.sauronsarmy.oopp.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * @author Jonatan Källman & Filip Labe
 * The class for the Map, the main model of the Map package.
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

    /**
     * Overloading so that context doesn't have to be sent once map is created.
     * @return : The map instance.
     */
    public static MapMVPInterface.ModelOps getInstance() {
        if (mapInstance == null){
            mapInstance = new Map();
        }
        return mapInstance;
    }

    //If there is not map yet, create one. Else, get the instance.

    /**
     * @param context: Context gotten from MainActivity on app start.
     * @return : The map instance.
     */
    public static MapMVPInterface.ModelOps getInstance(Context context) {
        if (mapInstance == null)
            mapInstance = new Map(context);
        return mapInstance;
    }

    /**
     * Constructor for map, used for testing (context free).
     */
    private Map () { //Overload for testing
        bgRef = R.drawable.mapbg;
        areas = createAreas();
        currentArea = areas[0];
        monfac = new monsterFactory();
    }

    /**
     * Constructor for map with context so that state and progress can be saved.
     */
    private Map (Context context) {
        bgRef = R.drawable.mapbg;
        areas = createAreas();
        currentArea = areas[0];
        monfac = new monsterFactory();
        loadState(context); //Load the map progress/ state
    }

    /**
     * Method for saving the map state.
     * @param context : Context used to save.
     */
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

    /**
     * Method for loading the map state.
     * @param context : State loaded that is sent by MainActivity.
     */
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

    //Sets the level to complete bases on the saved value

    /**
     * Method used to set levels to completed.
     * Used when a state is loaded and loads the goals achieved.
     * @param completed : Amount of completed goals. Goals are defeated monsters in a level.
     */
    private void setCompletedGoals(int completed){
        int com = completed;
        Log.i(TAG, "Goals: " + completedGoals);

        for (Area a : areas){
            for(int i = 0; i < a.getLevels().length; i++){
                if (com >0){
                    Log.i(TAG, ""+com);
                    a.completeLevel(i);
                    com--;
                }
            }
            a.checkComplete();

        }
    }

    /**
     * A method for damaging a monster.
     * @param damage : The damage that is dealt to the monster.
     * @return Returns the gold of the monster if it is killed (from Level.damageMonster method).
     */
    public int damageMonster(int damage) {
        int ret = getCurrentArea().getCurrentLevel().damageMonster(damage);
        if (ret > 0) {
            getCurrentArea().checkComplete();
            if(getCurrentLevel().getComplete() && !(getCurrentLevel().isChecked())){
                getCurrentArea().getCurrentLevel().setChecked(true);
                completedGoals++;
                Log.i(TAG, "Goals: " + completedGoals);
            }
            return ret;
        }
        return 0;
    }

    /**
     * A method used at map instantiation.
     * @return : Returns the Areas stated below.
     */
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

    @Override
    public int getLevelGoldMultiplier(int areaIndex, int levelIndex){
        Level level= areas[areaIndex].getLevels()[levelIndex];
        return level.getGoldMultiplier();
    }

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

    /**
     * Method used to check if it is possible to change to next level.
     * @return : true/ false depending on previous statement.
     */
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

    /**
     * Method used to check if it is possible to change to previous level.
     * @return : true/ false depending on previous statement.
     */
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

    /**
     * Method used to get the index of a Level.
     * @return : The index of the Level.
     */
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

    /**
     * Method used to switch both Area and Level.
     * Used after the last monster in the last level of an area is defeated.
     * @param level : The index of the Level that the method tries to change to.
     * @param area : The index of the Area that the method tries to change to.
     * @return : true/ false depending on if changing area and level was possible.
     */
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
                        changeArea(area);
                        changeLvl(level);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Method to change the current level.
     * @param index : Index of the new level.
     */
    private void changeLvl(int index) {
        Level newLevel = getCurrentArea().getLevels()[index];
        getCurrentArea().setCurrentLevel(newLevel);
    }

    /**
     * Method to change the current area.
     * @param index : Index of the new area.
     */
    private void changeArea(int index){
        setCurrentArea(getArea(index));
        int imgref= getArea(index).getImgRef();
        setBackgroundRef(imgref);
    }

    /**
     * Method to create an area.
     * @param areaIndex : Index of the area to create.
     * @return : The created area.
     */
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

    /**
     * Method used for testing mountain levels.
     * @param levelIndex : Index of the level to create.
     * @return : The created level.
     */
    public Level createMountainLvl(int levelIndex){
        return lvlfac.getMountainLevel(levelIndex);
    }

    /**
     * Method used for testing forest levels.
     * @param levelIndex : Index of the level to create.
     * @return : The created level.
     */
    public Level createForestLvl(int levelIndex){
        return lvlfac.getForestLevel(levelIndex);
    }

    /**
     * Method used for testing volcano levels.
     * @param levelIndex : Index of the level to create.
     * @return : The created level.
     */
    public Level createVolcanoLvl(int levelIndex){
        return lvlfac.getVolcanoLevel(levelIndex);
    }

}
