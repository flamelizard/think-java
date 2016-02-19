package com.gridworld.gameoflife;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;

/**
 * Created by Tom on 1/24/2016.
 */
/*
* Life bug follow these rules:
* 2-3 live neighbors => bug stays live, otherwise dies
* dead bug will resurrect if it has exactly 3 neighbors
* */
public class LifeBug extends Bug{
    public final Color aliveColor = Color.RED;
    public final Color deadColor = Color.BLACK;
    private boolean countingPhase = true;
    private Integer neighbors = 0;
    private int steps = 0;

    public LifeBug () {
        this.setColor(deadColor);
    }

    public LifeBug (Color bugColor) {
        this.setColor(bugColor);
    }

//    Single time step consists of two acts - counting neighbors and update
//    It is necessary for simultaneous update of each cell.
    public void act() {
        if (countingPhase) {
            neighbors = numLiveNeighbors();
            countingPhase = false;
            return;
        }

        if (isAlive()) {
            if (neighbors < 2 || neighbors > 3) {
                setDead();
            }
        } else {
            if (neighbors == 3) {
                setAlive();
            }
        }
        countingPhase = true;
        steps++;
        printNumSteps();
    }

    boolean isAlive() {
        return this.getColor() == aliveColor;
    }

    void setAlive() {
        this.setColor(aliveColor);
    }

    void setDead() {
        this.setColor(deadColor);
    }

    Integer numLiveNeighbors() {
        Integer num = 0;
        Grid<Actor> gr = this.getGrid();
        for(Actor a: gr.getNeighbors(this.getLocation())) {
            LifeBug bug = (LifeBug) a;
            if (bug.isAlive()) {
                num++;
            }
        }
        return num;
    }

    void printNumSteps() {
        if (steps % 200 == 0 && getLocation().equals(new Location(0, 0))) {
            System.out.println("[steps] " + steps);
        }
    }
}
