package com.example.sauronsarmy.oopp.clock;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
/**
 * Created by Filip on 2017-05-01.
 */

public class Runner {

    private static final Runner runnerInstance = new Runner();
    private Timer t;
    private Handler handler;
    private ArrayList<ClockListener> clockListeners;

    private Runner() {
        clockListeners = new ArrayList<>();
        t = new Timer();
    }

    public static Runner getInstance() {
        return runnerInstance;
    }


    public void start() {
        handler = new Handler();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (ClockListener cl:clockListeners){
                            cl.update();

                        }
                    }
                });
            }
        },1000, 1000);

    }
    public void stop(){
        t.cancel();

    }
    //Register to be notified every second
    public void register(ClockListener c){
        clockListeners.add(c);

    }
    //Unregister to no longer be notified
    public void unregister(ClockListener c){

        clockListeners.remove(c);
    }

}
