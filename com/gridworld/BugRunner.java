package com.gridworld;

/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * @author Cay Horstmann
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;

import java.awt.*;

/**
 * This class runs a world that contains a bug and a rock, added at random
 * locations. Click on empty locations to add additional actors. Click on
 * populated locations to invoke methods on their occupants. <br />
 * To build your own worlds, define your own actors and a runner class. See the
 * BoxBugRunner (in the boxBug folder) for an example. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Bug());
        world.add(new Rock());

        Bug blue_bug = new Bug();
        Color blue = new Color(0, 0, 255);
        blue_bug.setColor(blue);
        world.add(blue_bug);

//        moveBug(blue_bug, 5);
//      randomBug(blue_bug);
        wanderBug(blue_bug, 10);

//        Color green = Color.green;
//        colorBug(blue_bug, green);
        makeBugs(5, world);

        world.show();
    }

    public static void moveBug(Bug bug, int step) {
        String msg;
        while (step > 0) {
            while (!bug.canMove()) {
                msg = "Cannot move at " + bug.getLocation() + "turning around";
                System.out.println(msg);
                bug.turn();
            }
            bug.move();
            step--;
        }
    }

    public static void randomBug(Bug bug) {
        int angle = 270;
        double random = Math.random() * 100;

        if (random <= 25) {
            angle = 0;
        } else if (random <= 50) {
            angle = 90;
        } else if (random <= 75) {
            angle = 180;
        }
        bug.setDirection(angle);
        System.out.println("Bug direction " + bug.getDirection());

        if (bug.canMove()) {
            bug.move();
        }
    }

    public static void wanderBug(Bug bug, int repeat) {
        for (int i = 0; i < repeat; i++) {
            randomBug(bug);
            System.out.println("Bug at " + bug.getLocation());
        }
    }

    public static void colorBug(Bug bug, Color color) {
        System.out.println("Color changed -> " + color);
        bug.setColor(color);
    }

    public static void makeBugs(int n, ActorWorld world) {
        double red_lvl, blue_lvl;
        while (n > 0) {
            n--;
            Bug bug = new Bug();
            world.add(bug);
//            bug color is set based on coordinates
//            assume grid 9x9
            red_lvl = bug.getLocation().getRow() / 9.0 * 255;
            blue_lvl = bug.getLocation().getCol() / 9.0 * 255;
            System.out.println("Coordinates " + red_lvl + "," + blue_lvl);
//            typecast float to int
            Color color = new Color((int)red_lvl, 0, (int)blue_lvl);
            colorBug(bug, color);
        }
    }
}

