package com.gridworld.gameoflife;

import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 1/25/2016.
 */
/* Pattern or shape element that represent actor in Game of Life */
public class Patterns {
    //    Grid has switched axis - top horizontal y, left vertical x
    //    starting at 0, 0
    public static List<Location> rPentomimo(Location origin) {
        List<Location> locations = new ArrayList<>();
        Integer[] coordPairs = {0, 2, 0, 3, 1, 1, 1, 2, 2, 2};

//        calc diff to put each location relative to @origin
        Integer diffX = origin.getRow() - coordPairs[0];
        Integer diffY = origin.getCol() - coordPairs[1];

//        System.out.println("[diff] " + diff + coordPairs.toString());
        for (int i = 0; i < coordPairs.length; i = i + 2) {
            locations.add(new Location(coordPairs[i] + diffX, coordPairs[i + 1] + diffY));
        }
        return locations;
    }

//    better handling of putting pattern to custom location x, y
    public static List<Location> beacon1(int x, int y) {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location(x, y));
        locations.add(new Location(x+1, y));
        locations.add(new Location(x+2, y));
        locations.add(new Location(x, y-1));
        locations.add(new Location(x+1, y-2));
        locations.add(new Location(x+2, y-3));
        locations.add(new Location(x+3, y-3));
        locations.add(new Location(x+3, y-2));
        locations.add(new Location(x+4, y-2));
        locations.add(new Location(x+5, y-2));
        locations.add(new Location(x+6, y-1));
        locations.add(new Location(x+6, y));
//        incomplete, bored of typing locations

        return locations;
    }
}
