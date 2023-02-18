/**
 * TCSS 305 - Road Rage.
 */
package model;

import java.util.Map;

/**
 * Human class.
 * @author Logan Atkinson
 * @version 2/17/23
 */
public class Human extends AbstractVehicle {
    /**
     * Constant to store how long human stays dead for.
     */
    private static final int DEATH_TIME = 45;
    /**
     * Human constructor.
     * @param theX The x value to pass our human constructor
     * @param theY The y value to pass our human constructor
     * @param theDirection The direction to pass our human constructor
     */
    public Human(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
    }

    /**
     * Returns whether this object may move onto the given
     * terrain, when the streetlights are the given color.
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return whether this object may move onto the given type of
     *         terrain when the streetlights are the given color.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain == Terrain.GRASS //&& theLight != Light.RED)
                || theTerrain == Terrain.CROSSWALK && theLight != Light.GREEN;
    }
    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Direction tempDir;
        if (theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
            //return getDirection();
            tempDir = getDirection();
        } else if (theNeighbors.get(getDirection().left())
                == Terrain.CROSSWALK) {
            //return getDirection().left();
            tempDir = getDirection().left();
        } else if (theNeighbors.get(getDirection().right())
                == Terrain.CROSSWALK) {
            //return getDirection().right();
            tempDir = getDirection().right();
        } else {
            /*return getStraightLeftRightStream().
                    filter(x -> isValidTerrain(theNeighbors.get(x))).
                    sorted(SHUFFLE).findFirst().orElse(getDirection().reverse());*/
            tempDir = getStraightLeftRightStream().
                    filter(x -> isValidTerrain(theNeighbors.get(x))).
                    sorted(SHUFFLE).findFirst().orElse(getDirection().reverse());
        }
        return tempDir;
    }
    /**
     * Checks to see if a given terrain is valid.
     * @param theTerrain the terrain to check
     * @return true if it is valid, and false if not
     */
    private boolean isValidTerrain(final Terrain theTerrain) {
        return theTerrain == Terrain.GRASS || theTerrain == Terrain.CROSSWALK;
    }
}
