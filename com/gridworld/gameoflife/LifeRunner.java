package com.gridworld.gameoflife;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tom on 1/24/2016.
 */
/* Zero-player Game of Life
*
* Improvements:
* Detect stable configuration, that is when live cells does not change
* location
*
* Status: done
* */
public class LifeRunner {
    static int rows;
    static int cols;
    private static ActorWorld world;

    public static void main(String[] args) {
        world = new ActorWorld(new UnboundedGrid<>());
        populateWorld(world);

        Grid gr = world.getGrid();
        placePattern(Patterns.rPentomimo(getGridCenter()));
        placePattern(Patterns.beacon1(20, 20));
        world.show();
    }

    static Location getGridCenter() {
        return new Location(rows / 2, cols / 2);
    }

    static void populateWorld(ActorWorld world) {
        Grid<Actor> gr = world.getGrid();
        if (gr instanceof UnboundedGrid) {
            rows = 100;
            cols = 100;
        } else {
            rows = gr.getNumRows();
            cols = gr.getNumCols();
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                world.add(new Location(row, col), new LifeBug());
            }
        }
    }

    static void placePattern(List<Location> locations) {
        for (Location loc : locations) {
            Grid<Actor> gr = world.getGrid();
            LifeBug bug = (LifeBug) gr.get(loc);
            bug.setAlive();
        }
    }

    //    @coords: list of location coordinate's pairs
    static List<Location> locationsBuilder(List<Integer> coords) {
        List<Location> locations = new LinkedList<>();
        for (int i = 0; i < coords.size(); i = i + 2) {
            Location loc = new Location(coords.get(i), coords.get(i + 1));
            locations.add(loc);
        }
        return locations;
    }
}
