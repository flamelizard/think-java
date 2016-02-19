package com.gridworld.sparsegrid;

/**
 * Created by Tom on 2/10/2016.
 */

/*
* Interesting implementation of linked objects as alternative to using
* arraylist for storing Actor objects
*
* Principle
* - each row holds onto only single instance of SparseGridNode
* - this instance is basically chained sequence of objects linked through
* instance var "next"
* - "next" holds another SparseGridNode instance that shares the same row
* - actual Actor object is stored in instance var "occ"
*
* Hard to grasp since only single instance per row is stored but this very
* instance links number of another instances in memory
*
* */
public class SparseGridNode {
    private Object occ;
    private int col;
    private SparseGridNode next;

    public SparseGridNode(Object occ, int col, SparseGridNode next) {
        this.occ = occ;
        this.col = col;
        this.next = next;
    }

    public Object getOccupant() {
        return occ;
    }

    public int getCol() {
        return col;
    }

    public SparseGridNode getNext() {
        return next;
    }

    public void setNext(SparseGridNode node) {
        next = node;
    }
}
