/*
 * TCSS 305 - Road Rage
 */

package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.*;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for class Truck.
 *
 * @author Logan Atkinson
 * @version Winter 2023
 */
public class TruckTest {

    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;

    /** Test method for TRUCK constructor. */
    @Test
    public void testTruckConstructor() {
        final Truck t = new Truck(10, 11, Direction.NORTH);

        assertEquals(10, t.getX(), "Truck x coordinate not initialized correctly!");
        assertEquals(11, t.getY(), "Truck y coordinate not initialized correctly!");
        assertEquals(Direction.NORTH, t.getDirection(), "Truck direction not initialized correctly!");
        assertEquals(0, t.getDeathTime(), "Truck death time not initialized correctly!");
        assertTrue(t.isAlive(), "Truck isAlive() fails initially!");
    }

    /** Test method for Truck setters. */
    @Test
    public void testTruckSetters() {
        final Truck t = new Truck(10, 11, Direction.NORTH);

        t.setX(12);
        assertEquals(12, t.getX(), "Truck setX failed!");
        t.setY(13);
        assertEquals(13, t.getY(), "Truck setY failed!");
        t.setDirection(Direction.SOUTH);
        assertEquals(Direction.SOUTH, t.getDirection(), "Truck setDirection failed!");
    }

    /**
     * Test method for {@link Truck#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {

        //Trucks can move on streets and through lights and crosswalks
        // So we need to test the three of those, and test to make sure
        // that our trucks are not going through any other types of terrains

        //Directions are random
        // They drive through all streetlights but stop for
        // red crosswalks

        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.CROSSWALK);
        validTerrain.add(Terrain.LIGHT);

        final Truck t = new Truck(0, 0, Direction.NORTH);
        // test each terrain type as a destination
        for (final Terrain destinationTerrain : Terrain.values()) {
            // try the test under each light condition
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain == Terrain.STREET) {

                    // humans can pass GRASS under any light condition
                    assertTrue(t.canPass(destinationTerrain, currentLightCondition),
                            "Truck should be able to pass STREET with light "
                                    + currentLightCondition);
                } else if (destinationTerrain == Terrain.CROSSWALK) {
                    // Truck can pass CROSSWALK
                    // if the light is YELLOW or GREEN but not RED

                    if (currentLightCondition != Light.RED) {
                        assertTrue(t.canPass(destinationTerrain, currentLightCondition),
                                "Truck should be able to pass " + destinationTerrain
                                        + ", with light " + currentLightCondition);
                    } else { // light is yellow or red
                        assertFalse(t.canPass(destinationTerrain, currentLightCondition),
                                "Truck should NOT be able to pass " + destinationTerrain
                                        + ", with light " + currentLightCondition);
                    }
                } else if (!validTerrain.contains(destinationTerrain)) {

                    assertFalse(t.canPass(destinationTerrain, currentLightCondition),
                            "Truck should NOT be able to pass " + destinationTerrain
                                    + ", with light " + currentLightCondition);
                }
            }
        }
    }

    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionSurroundedByStreet() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.NORTH, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.STREET);
        neighbors.put(Direction.SOUTH, Terrain.STREET);

        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;

        final Truck t = new Truck(0, 0, Direction.NORTH);

        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            final Direction d = t.chooseDirection(neighbors);

            if (d == Direction.WEST) {
                seenWest = true;
            } else if (d == Direction.NORTH) {
                seenNorth = true;
            } else if (d == Direction.EAST) {
                seenEast = true;
            } else if (d == Direction.SOUTH) { // this should NOT be chosen
                seenSouth = true;
            }
        }

        assertTrue(seenWest && seenNorth && seenEast,
                "Truck chooseDirection() fails to select randomly "
                        + "among all possible valid choices!");

        assertFalse(seenSouth, "Truck chooseDirection() reversed direction when not necessary!");
    }


    /**
     * Test method for {@link Human#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionOnStreetMustReverse() {

        for (final Terrain t : Terrain.values()) {
            if (t != Terrain.STREET && t != Terrain.CROSSWALK && t != Terrain.LIGHT) {

                final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
                neighbors.put(Direction.WEST, t);
                neighbors.put(Direction.NORTH, t);
                neighbors.put(Direction.EAST, t);
                neighbors.put(Direction.SOUTH, Terrain.GRASS);

                final Truck truck = new Truck(0, 0, Direction.NORTH);

                // the Human must reverse and go SOUTH
                assertEquals(Direction.SOUTH, truck.chooseDirection(neighbors),
                        "Truck chooseDirection() failed "
                                + "when reverse was the only valid choice!");
            }

        }
    }
}