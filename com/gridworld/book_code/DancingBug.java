package com.gridworld.book_code;

import info.gridworld.actor.Bug;

/**
 * Created by Tom on 12/9/2015.
 */

/*
Dancing bug will turn on each turn many times according to array rhythm
 */
public class DancingBug extends Bug {
    private int[] rhythm;
    private int current = 0;

    public DancingBug(int[] rhythm) {
//        !! BAD programming practice, do not use exceptions to control flow
//          or to recover from something expected.
//        if (rhythm.length == 0) {
//            throw new IndexOutOfBoundsException("Rhythm must not be an empty array");
//        }
        this.rhythm = rhythm;
    }

    public void act() {
        while (!canMove()) {
            turn();
        }
        for (int i =0; i < rhythm[current]; i++) {
            System.out.println(i);
            turn();
        }
        current++;
        if (current >= rhythm.length-1) {
            current = 0;
        }
        move();
    }
}
