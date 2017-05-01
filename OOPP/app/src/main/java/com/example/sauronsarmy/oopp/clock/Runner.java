package com.example.sauronsarmy.oopp.clock;

import android.os.Handler;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Filip on 2017-05-01.
 */

public class Runner {

    Timer t = new Timer();

    ArrayList<ClockListener> clockListeners = new ArrayList<>();


    public void start() {

        /*t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (ClockListener cl:clockListeners){
                    cl.run();
                    System.out.println("Clock");
                }
            }
        },1000, 1000);*/

        final Handler h = new Handler();


        Runnable runnable = new Runnable() {
            private long startTime = System.currentTimeMillis();
            public void run() {

                h.post(new Runnable() {
                    @Override
                    public void run() {

                        for (ClockListener cl:clockListeners){
                            cl.run();
                            System.out.println("Clock");
                        }
                    }
                });

            }



        };
        new Thread(runnable).start();


    }
    public void stop(){
        t.cancel();

    }

    public void register(ClockListener c){
        clockListeners.add(c);

    }

    public void unregister(ClockListener c){

        clockListeners.remove(c);
    }

}
