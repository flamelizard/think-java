package com.gridworld.book_code;
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
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.*;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BoxBugRunner
{
    private static ActorWorld world = null;

//    public BoxBugRunner()   {
//    }

    public static void main(String[] args)
    {
        world = new ActorWorld();
//        ActorWorld world = new ActorWorld();
//        boxBugs(world);
//        ZBugs(world);
        DancingBugs();
        world.show();
    }

    public static void boxBugs(ActorWorld world) {
        BoxBug alice = new BoxBug(6);
        alice.setColor(Color.ORANGE);
        BoxBug bob = new BoxBug(3);
        world.add(new Location(7, 8), alice);
        world.add(new Location(5, 5), bob);
//        System.out.println(alice.getDirection());
    }

    public static void ZBugs(ActorWorld world) {
        int cols, rows;
        cols = world.getGrid().getNumCols();
        rows = world.getGrid().getNumRows();
        Point center = new Point(cols, rows);

        ZBug mark = new ZBug(6);
        world.add(new Location(3, 3), mark);
    }

    public static void DancingBugs() {
        int[] dance = {2, 1, 0, 2, 1, 0};
//        System.out.println(dance.length);
        DancingBug lucy = new DancingBug(dance);
        world.add(new Location(3, 3), lucy);
    }
}