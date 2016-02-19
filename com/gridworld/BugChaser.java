package com.gridworld;

/**
 * Created by Tom on 10/18/2015.
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

import java.awt.*;

/**
 * This class runs a world that contains a bug and a rock, added at random
 * locations. Click on empty locations to add additional actors. Click on
 * populated locations to invoke methods on their occupants. <br />
 * To build your own worlds, define your own actors and a runner class. See the
 * BoxBugRunner (in the boxBug folder) for an example. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BugChaser
{
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        Bug b1 = new Bug(Color.blue);
        Bug b2 = new Bug(Color.red);

        Location loc1 = new Location(1, 1);
        Location loc2 = new Location(2, 1);

        world.add(loc1, b1);
        world.add(loc2, b2);

        int x1, y1, x2, y2;
        x1 = b1.getLocation().getCol();
        y1 = b1.getLocation().getRow();
        x2 = b2.getLocation().getCol();
        y2 = b2.getLocation().getRow();

        echo(distance(x1, y1, x2, y2));
        b1.turn();
//        System.out.println(b1.getDirection() + b2.getDirection());
//        b1.turn();
//        System.out.println(b1.getDirection() + b2.getDirection());

        double angle = Math.atan2(x2-x1, y2-y1);
        System.out.println("atan2 " + angle);

        world.show();
    }

    public static double distance(int x1, int y1, int x2, int y2) {
//        Pythagoras theorem to cal side c in triangle a-b-c
//        Negative values will disappear after squaring
        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));

    }

    public static void echo(String s) {
        System.out.println(s);
    }

    public static void echo(int i) {
        System.out.println(i);
    }

    public static void echo(double i) {
        System.out.println(i);
    }
}

