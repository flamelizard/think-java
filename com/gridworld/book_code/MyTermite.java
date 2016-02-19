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

/**
 * A termite that picks up a flower if it sees one and 
 * drops a flower if it has one.
 */
public class MyTermite extends Termite {

    /*
     * Does that thing that termites do. 
     */
    public void act() {
        if (getGrid() == null)
            return;
	
		if (seeFlower()) {
            if (hasFlower()) {
                dropFlower();
            } else {
			    pickUpFlower();
            }
		}

		if (canMove()) {
		move();
		}
		randomTurn();
    }

//    public void act() {
//        if (getGrid() == null)
//            return;
//
//        if (seeFlower()) {
//            pickUpFlower();
//        }
//        if (hasFlower()) {
//            dropFlower();
//        }
//
//        if (canMove()) {
//            move();
//        }
//        randomTurn();
//    }
}
