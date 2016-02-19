package com.gridworld.sparsegrid.test;

import com.gridworld.sparsegrid.SparseBoundedGridV2;
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.awt.*;

import static org.hamcrest.CoreMatchers.startsWith;

/**
 * Created by Tom on 2/4/2016.
 */
public class SparseGridTest {
    ActorWorld world;
    Grid<Actor> gr;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

//    @Before
    public void setup() {
        if (world != null) {
            world = null;
        }
//        world = new ActorWorld (new SparseBoundedGrid<>(10, 10));
        world = new ActorWorld(new SparseBoundedGridV2<>(20, 20));
        gr = world.getGrid();
    }

    @Test
    public void smokeTest() {

        System.out.println("Checking basic grid methods");
//        Grid<Actor> gr = world.getGrid();
        setup();

        Assert.assertEquals(10, gr.getNumCols());
        Assert.assertEquals(10, gr.getNumRows());

        Assert.assertEquals(true, gr.isValid(new Location(0, 0)));
        Assert.assertEquals(true, gr.isValid(new Location(9, 9)));

        Assert.assertEquals(false, gr.isValid(new Location(0, -1)));
        Assert.assertEquals(false, gr.isValid(new Location(-1, 0)));
        Assert.assertEquals(false, gr.isValid(new Location(10, 10)));

        System.out.println("Adding and removing actors");
        Assert.assertEquals(0, gr.getOccupiedLocations().size());
        world.add(new Bug());
        world.add(new Bug());
        world.add(new Bug());
        Assert.assertEquals(3, gr.getOccupiedLocations().size());

//        test .remove and .getOccupiedLoc
        Location loc = gr.getOccupiedLocations().get(0);
        world.remove(loc);
        Assert.assertEquals(2, gr.getOccupiedLocations().size());

        Assert.assertNull(world.remove(loc));
    }

    @Test
    public void exceptionHandlingTest() {
        setup();
//        Uses ExpectedException class
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid location");

        world.add(new Location(-1, -1), new Bug());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGridGet() {
        setup();
        gr.get(new Location(-100, -1));

        Assert.assertNull(gr.get(new Location(0, 0)));

        world.add(new Location(0, 0), new Bug(Color.blue));
        Assert.assertTrue(gr.get(new Location(0, 0)) instanceof Bug);
    }

    @Test
    public void testGridAdd() {
        setup();
        try {
            world.add(new Location(5, -1), new Bug());
        } catch (IllegalArgumentException e) {
            Assert.assertThat(e.getMessage(), startsWith("Invalid"));
        }

// Cannot pass null, an internal methods tries to call it
//        try {
//            world.add(null);
//        } catch (IllegalArgumentException e) {
//            Assert.assertThat(e.getMessage(), containsString("cannot be null"));
//        }
    }

    @Test
    public void testSparseGridNode() {
        setup();
//        populate world
        int actors = 50;
        for (int i = 0; i < actors; i++) {
            world.add(new Bug());
        }
        Assert.assertEquals(actors, gr.getOccupiedLocations().size());

        Location loc = new Location(5, 5);
        Bug bug1 = new Bug(Color.blue);
        world.add(loc, bug1);

        Bug bug2 = (Bug)world.remove(loc);
        Assert.assertTrue("Removed bug is different from the added one",
                bug2 == bug1 );

        Assert.assertNull(world.remove(loc));
        gr.getOccupiedLocations();
    }


    @After
    public void tearDown() {
        world = null;
    }
}
