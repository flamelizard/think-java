package com.gridworld.book_code;
/*
 * This program is part of an exercise in Think Java (thinkapjava.com)
 * Copyright(c) 2011 Allen B. Downey (http://allendowney.com)
 *
 * It is based on the AP(r) Computer Science GridWorld Case Study.
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
 * @author Allen B. Downey
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.UnboundedGrid;

/**
 * This class runs a world that contains Termites. <br />
 */
public class LangtonRunner {
    
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld(new UnboundedGrid<>());
//        makeFlowers(world, 20);
        
//        Termite alice = new Termite();
        LangtonTermite alice = new LangtonTermite();
        world.add(alice);
        
        world.show();
    }
    
//    public static void makeFlowers(ActorWorld world, int n) {
//        for (int i=0; i<n; i++) {
//            world.add(new EternalFlower());
//        }
//    }
}