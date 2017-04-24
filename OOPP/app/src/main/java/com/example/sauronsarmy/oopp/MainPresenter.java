package com.example.sauronsarmy.oopp;
import java.lang.ref.WeakReference;

/**
 * Created by Jonatan on 24/04/2017.
 */

class MainPresenter implements MainMVPInterface.PresenterOps {
    // View reference

    private static final MainPresenter ourInstance = new MainPresenter();


    PlayerModel player = PlayerModel.getInstance();
    Shop shop = Shop.getInstance();
    Map map = Map.getInstance();


    monsterFactory monFac=new monsterFactory();


    WeakReference<MainMVPInterface.ViewOps> mView;

    public MainPresenter() {


    }

    public static MainPresenter getInstance(){

        return ourInstance;
    }
    // A configuration changed
    @Override
    public void onConfigChange(MainMVPInterface.ViewOps view) {
                mView = new WeakReference<>(view);
                setNewMonster();
    }

    @Override
    public void onDestroy(boolean isChangingConfig){} //To be implemented
    @Override
    public void onError(String msg){} //To be implemented


    public void monsterClicked(){
        Monster currentMonster = map.getCurrentArea().getCurrentLevel().getCurrentMonster();
        if(currentMonster.damageMonster(player.getDamage())){

            player.setMoney(player.getMoney()+currentMonster.getGold());
            setNewMonster();
        }
    }

    private void setNewMonster(){

        map.getCurrentArea().getCurrentLevel().setCurrentMonster(monFac.getMonster(map.getCurrentArea().getCurrentLevel().getHealthMultiplier(),
                map.getCurrentArea().getCurrentLevel().getGoldMultiplier(),map.getCurrentArea().getCurrentLevel().getArea()));

    }

    public Monster getCurrentMonster(){


        return map.getCurrentArea().getCurrentLevel().getCurrentMonster();
    }
}
