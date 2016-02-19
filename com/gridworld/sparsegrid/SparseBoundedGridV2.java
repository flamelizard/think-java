package com.gridworld.sparsegrid;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * Created by Tom on 2/10/2016.
 */

// The grid uses different implementation of sparse grid
public class SparseBoundedGridV2<E> extends AbstractGrid<E> {
    private int row;
    private int col;
    private SparseGridNode[] occupants;

    public SparseBoundedGridV2(int r, int c) {
        row = r;
        col = c;
        occupants = new SparseGridNode[row];
    }

    public int getNumRows() {
        return row;
    }

    public int getNumCols() {
        return col;
    }

    public boolean isValid(Location loc) {
        return loc.getRow() >= 0 && loc.getRow() < getNumRows() &&
                loc.getCol() >= 0 && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        for (int r = 0; r < occupants.length; r++) {
            SparseGridNode head = occupants[r];
            while (head != null) {
                locations.add(new Location(r, head.getCol()));
                head = head.getNext();
            }
        }
//        printSparseGridNodes();
        return locations;
    }

    public E put(Location loc, E obj) {
        E oldOcc = null;
        if (isValid(loc)) {
            oldOcc = remove(loc);
            SparseGridNode head = occupants[loc.getRow()];
            occupants[loc.getRow()] = new SparseGridNode(obj, loc.getCol(),
                    head);
        }
        return oldOcc;
    }

    public E remove(Location loc) {
        E oldOcc = null;
        if (isValid(loc)) {
            oldOcc = get(loc);
            SparseGridNode node = occupants[loc.getRow()];
            SparseGridNode previous = null;
            while (node != null) {
                if (loc.getCol() == node.getCol()) {
//                    head (first) node matches
                    if (previous == null) {
                        occupants[loc.getRow()] = node.getNext();
                    } else {
//                        non-head node, relink only
                        previous.setNext(node.getNext());
                    }
                    break;
                }
                previous = node;
                node = node.getNext();
            }
        }
        return oldOcc;
    }

    public E get(Location loc) {
        if (isValid(loc)) {
            SparseGridNode head = occupants[loc.getRow()];
            while (head != null) {
                if (loc.getCol() == head.getCol()) {
                    return (E)head.getOccupant();
                }
                head = head.getNext();
            }
        }
        return null;
    }

    /*
    StringBuilder cannot take advantage of String.join and the likes as it
    is not iterable.
    However, its overloaded .append is very handy for adding vars of any kind
    without explicit cast.
    */
    public void printSparseGridNodes() {
        System.out.println("[sparse grid node printer]");
        String sep = ", ";
        int row = 0;
        for (SparseGridNode node : occupants) {
            while (node != null) {
                StringBuilder s = new StringBuilder();
                s.append(String.format("[%s, %s]", row, node.getCol()))
                        .append(sep);
                s.append(node.getOccupant()).append(sep);
                s.append("\n\t-->").append(node.getNext());
                System.out.println(s);
                node = node.getNext();
            }
            row++;
        }
    }
}
