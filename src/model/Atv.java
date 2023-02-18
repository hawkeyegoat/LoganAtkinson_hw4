/**
 * TCSS 305 - Road Rage
 */
package model;

import java.util.Map;

/**
 * Atv class.
 * @author Logan Atkinson
 * @version 2/17/23
 */
public class Atv extends AbstractVehicle {
    /**
     * Constant to store how long our Atv should stay dead.
     */
    private static final int DEATH_TIME = 25;

    /**
     * Atv constructor.
     * @param theX the x value to pass our constructor
     * @param theY the y value to pass our constructor
     * @param theDirection the direction to pass our constructor
     */
    public Atv(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
    }

    /**
     * Returns whether this object may move onto the given type of
     * terrain, when the streetlights are the given color.
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether this object may move onto the given type of
     * terrain when the streetlights are the given color.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain != Terrain.WALL;
    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        return getStraightLeftRightStream().
                filter(x -> isValidTerrain(theNeighbors.get(x))).
                sorted(SHUFFLE).findFirst().orElse(getDirection().reverse());
    }
    /**
     * Checks to see if a given terrain is valid.
     * @param theTerrain the terrain to check
     * @return true if it is valid, and false if not
     */
    private boolean isValidTerrain(final Terrain theTerrain) {

        return theTerrain != Terrain.WALL;
    }
}
