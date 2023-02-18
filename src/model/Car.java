/*
 * TCSS 305 - Road Rage
 */
package model;

import java.util.Map;


 /**
  * Car class.
  * @author Logan Atkinson
  * @version 2/17/23
  */

public class Car extends AbstractVehicle {
     /**
      * protected constant for the cars death time.
      */
    protected static final int DEATH_TIME = 15;

     /**
      * Car constructor.
      * @param theX the x value to pass our constructor
      * @param theY the y value to pass our constructor
      * @param theDirection the direction to pass our constructor
      */
    public Car(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
    }

    /**
     * Returns whether this object may move onto the given
     * terrain, when the streetlights are the given color.
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether this object may move onto the given type of
     * terrain when the streetlights are the given color.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain == Terrain.STREET //&& theLight != Light.RED
                || theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN
                || theTerrain == Terrain.LIGHT && theLight != Light.RED;
    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Direction tempDir;
        if (isValidTerrain(theNeighbors.get(getDirection()))) {
            //return getDirection();
            tempDir = getDirection();
        } else if (isValidTerrain(theNeighbors.get(getDirection().left()))) {
            tempDir = getDirection().left();
            //return getDirection().left();
        } else if (isValidTerrain(theNeighbors.get(getDirection().right()))) {
            tempDir = getDirection().right();
            //return getDirection().right();
        } else {
            tempDir = getDirection().reverse();
            //return getDirection().reverse();
        }
        return tempDir;
    }

     /**
      * Checks to see if a given terrain is valid.
      * @param theTerrain the terrain to check
      * @return true if it is valid, and false if not
      */
    private boolean isValidTerrain(final Terrain theTerrain) {
        return theTerrain == Terrain.STREET
                || theTerrain == Terrain.CROSSWALK
                || theTerrain == Terrain.LIGHT;
    }
}
