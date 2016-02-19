package com.gridworld.sparsegrid.test;

import com.gridworld.sparsegrid.SparseBoundedGridV2;
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * Created by Tom on 2/4/2016.
 */
public class SparseGridV2Test {
    ActorWorld world;
    Grid<Actor> gr;
    int row = 20;
    int col = 20;


    //    @Before
    public void setup() {
        if (world != null) {
            world = null;
        }
        world = new ActorWorld(new SparseBoundedGridV2<>(row, col));
        gr = world.getGrid();
    }

    @Test
    public void basics() {
        setup();
        Location loc = new Location(5, 6);
        world.add(loc, new Bug());
        gr.getOccupiedLocations();
    }

    @Test
    public void smokeTest() {
        setup();

        Assert.assertEquals(col, gr.getNumCols());
        Assert.assertEquals(row, gr.getNumRows());

        System.out.println("Check location validity");
        Assert.assertEquals(true, gr.isValid(new Location(0, 0)));
        Assert.assertEquals(true, gr.isValid(new Location(9, 9)));

        Assert.assertEquals(false, gr.isValid(new Location(0, -1)));
        Assert.assertEquals(false, gr.isValid(new Location(-1, 0)));
        Assert.assertEquals(false, gr.isValid(new Location(row, col)));

        System.out.println("Adding and removing actors");
        Assert.assertEquals(0, gr.getOccupiedLocations().size());
        world.add(new Bug());
        world.add(new Bug());
        world.add(new Bug());
        Assert.assertEquals(3, gr.getOccupiedLocations().size());

        System.out.println("Remove bug");
        Location loc = gr.getOccupiedLocations().get(0);
        world.remove(loc);
        Assert.assertEquals(2, gr.getOccupiedLocations().size());

        Assert.assertNull(world.remove(loc));
    }


    @Test
    public void testSparseGridNode() {
        setup();
        System.out.println("Populate world, check count");
        int actors = 10;
        for (int i = 0; i < actors; i++) {
            world.add(new Bug());
        }
        Assert.assertEquals(actors, gr.getOccupiedLocations().size());

        System.out.println("Add / remove bug");
        Location loc = new Location(5, 5);
        Bug bug1 = new Bug(Color.blue);
        world.add(loc, bug1);

        Bug bug2 = (Bug) world.remove(loc);
        Assert.assertTrue("Removed bug is different from the added one",
                bug2 == bug1);

        Assert.assertNull(world.remove(loc));
    }


    @After
    public void tearDown() {
        world = null;
    }
}
