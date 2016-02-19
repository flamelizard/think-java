package com.gridworld.sparsegrid;

/**
 * Created by Tom on 2/1/2016.
 */

/*
Class using generics <E> where E stands for any kind of object. E is just an
placeholder to abstract variable type to be used.
It is up to user to provide actual type (String, Date...) when instantiated.
*/
public class OccupantInCol<E> {
    private E occupant;
    private int col;

    public OccupantInCol(E occ, int col) {
        occupant = occ;
        this.col = col;
    }

    public E getOccupant() {
        return occupant;
    }

    public int getCol() {
        return col;
    }
}
