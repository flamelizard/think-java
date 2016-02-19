package com.gridworld.book_code;

import info.gridworld.actor.Bug;

/**
 * Created by Tom on 12/8/2015.
 */

/*
A Bug that draws letter Z on the grid and stops moving afterward
@param length is horizontal size of letter Z
 */
public class ZBug extends Bug{
    private int length;
    private int steps = 0;
    private int turns = 0;

    public ZBug(int length) {
        this.setDirection(90);
        this.length = length;
    }

    public void act() {

        if (!canMove() || turns >= 2) {
            return;
        }

        if (steps < length-1) {
            move();
            steps++;
        } else {
            if (getDirection() == 90) {
                turns++;
                if (turns == 2) {
                    move();
                }
                setDirection(225);
            } else {
                setDirection(90);
            }
            steps = 0;
        }
    }
}
