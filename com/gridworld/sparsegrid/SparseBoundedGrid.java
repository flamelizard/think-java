package com.gridworld.sparsegrid;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Tom on 2/1/2016.
 */

//    E is formal type parameter substituting any kind of Actor class
public class SparseBoundedGrid<E> extends AbstractGrid<E> {
    //    array represents rows, each list holds object of actors in the given row
    ArrayList<LinkedList<OccupantInCol<E>>> occupants;
    private Integer rows;
    private Integer cols;

    public SparseBoundedGrid(int col, int row) {
        this.rows = row;
        this.cols = col;
        occupants = new ArrayList<>();
        while (row > 0) {
            occupants.add(new LinkedList<>());
//            cols are added dynamically to accommodate object
            row--;
        }
    }

    public int getNumRows() {
        return rows;
    }

    public int getNumCols() {
        return cols ;
    }

    public boolean isValid(Location loc) {
        return loc.getRow() >= 0 && loc.getRow() < getNumRows() &&
                loc.getCol() >= 0 && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations() {
//        System.out.println("[get occupied]");
        ArrayList<Location> locations = new ArrayList<>();
        int rowId = 0;
        for (LinkedList<OccupantInCol<E>> row : occupants) {
            for (OccupantInCol<E> col : row) {
                locations.add(new Location(rowId, col.getCol()));
            }
            rowId++;
        }
        return locations;
    }

    public E put(Location location, E obj) {
        E oldOcc = null;

        if (!isValid(location)) {
            throw new IllegalArgumentException("Invalid location");
        } else if (obj == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }

        int i = indexOfOccupant(occupants.get(location.getRow()), location
                .getCol());

        if (i != -1) {
            oldOcc = occupants.get(location.getRow()).remove(i).getOccupant();
        }

        occupants.get(location.getRow()).add(new OccupantInCol<E>(obj,
                location.getCol()));
        return oldOcc;
    }

    public E get(Location location) {
        if (!isValid(location)) {
            throw new IllegalArgumentException("Invalid location");
        }

        int i = indexOfOccupant(occupants.get(location.getRow()), location
                .getCol());
        if (i != -1) {
            return occupants.get(location.getRow()).get(i).getOccupant();
        }
        return null;
    }

    public E remove(Location location) {
        if (!isValid(location)) {
            throw new IllegalArgumentException("Location is not valid");
        }
        int i = indexOfOccupant(occupants.get(location.getRow()), location
                .getCol());
        if (i == -1) {
            return null;
        }
        return occupants.get(location.getRow()).remove(i).getOccupant();
    }


    /*
    Return index to list where to find object at specified column

    @param list is list of objects occupying single row
    @param col is column number to identify occupant
    */
    int indexOfOccupant(LinkedList<OccupantInCol<E>> list, int col) {
        int idx = -1;
        if (list == null || list.size() == 0) {
            return -1;
        }
        for (OccupantInCol<E> occ : list) {
            idx++;
            if (col == occ.getCol()) {
                return idx;
            }
        }
        return -1;
    }
}
