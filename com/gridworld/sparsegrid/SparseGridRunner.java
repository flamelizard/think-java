package com.gridworld.sparsegrid;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.grid.BoundedGrid;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tom on 2/3/2016.
 */
public class SparseGridRunner {
    static ActorWorld world;

    public static void main(String[] args) {
//        setupWorld();
//        timeCodeTemplate(10);
        runWorld();
    }

    public static void runWorld() {
//        ActorWorld world = new ActorWorld(new SparseBoundedGrid<>(10, 10));
        ActorWorld world = new ActorWorld();
        world.addGridClass("com.gridworld.sparsegrid.SparseBoundedGrid");
        world.add(new Bug(Color.blue));
        world.show();
    }

    public static void setupWorld() {
        int row = 100;
        int col = 100;
        world = new ActorWorld(new BoundedGrid<>(row, col));

//        populate grid randomly
        for (int i = 0; i < 100; i++) {
            world.add(new Bug());
        }
    }

    public static void profileThisCode() {
        world.add(new Bug());
    }

//    time profile code, very basic
    public static void timeCodeTemplate(int loops) {
        ArrayList<Long> loopTimes = new ArrayList<>();
        for (int i =  0; i < loops; i++) {
            long start = System.currentTimeMillis();
            profileThisCode();
            long stop = System.currentTimeMillis();
            loopTimes.add(stop - start);
        }

        Long sum = (long) 0;
        for(Long time: loopTimes) {
            sum += time;
        }

        System.out.println("Average time: " + sum / loops + "ms");
        System.out.println(loopTimes.toString());
    }

}
