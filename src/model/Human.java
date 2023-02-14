package model;

import java.util.Map;
import java.util.Objects;

public class Human extends AbstractVehicle {

    private static final int DEATH_TIME = 45;

    /**
     * Constructor
     */
    public Human(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
    }

    /**
     * Returns whether or not this object may move onto the given type of
     * terrain, when the street lights are the given color.
     *
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return whether or not this object may move onto the given type of
     *         terrain when the street lights are the given color.
     */
    @Override

    public boolean canPass(Terrain theTerrain, Light theLight) {
        return (theTerrain == Terrain.GRASS) //&& theLight != Light.RED)
                || (theTerrain == Terrain.CROSSWALK && theLight != Light.RED);
    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        return getStraightLeftRightStream().
                filter(x -> isValidTerrain(theNeighbors.get(x))).
                sorted(SHUFFLE).findFirst().orElse(getDirection().reverse());

    }
    private boolean isValidTerrain (final Terrain theTerrain) {
        return theTerrain == Terrain.GRASS || theTerrain == Terrain.CROSSWALK;
    }

    /**
     * Called when this Vehicle collides with the specified other Vehicle.
     *
     * @param theOther The other object.
     */


    /**
     * Returns the number of updates between this vehicle's death and when it
     * should be revived.
     *
     * @return the number of updates.
     */
    //@Override
    //public int getDeathTime() {
        //timer = 45;
       // return ;
    //}

    /**
     * Returns the file name of the image for this Vehicle object, such as
     * "car.gif".
     *
     * @return the file name.
     */

    /**
     * Returns this Vehicle object's direction.
     *
     * @return the direction.
     */
    //public Direction getDirection() {
     //   return myDirection;
    //}/

    /**
     * Returns this Vehicle object's x-coordinate.
     *
     * @return the x-coordinate.
     */
   // public int getX() {
      //  return myX;
    //}

    /**
     * Returns this Vehicle object's y-coordinate.
     *
     * @return the y-coordinate.
     */

    /**
     * Returns whether this Vehicle object is alive and should move on the map.
     *
     * @return true if the object is alive, false otherwise.
     */


    /**
     * Called by the UI to notify a dead vehicle that 1 movement round has
     * passed, so that it will become 1 move closer to revival.
     */


    /**
     * Moves this vehicle back to its original position.
     */


    /**
     * Sets this object's facing direction to the given value.
     *
     * @param theDir The new direction.
     */

    /**
     * Sets this object's x-coordinate to the given value.
     *
     * @param theX The new x-coordinate.
     */


    /**
     * Sets this object's y-coordinate to the given value.
     *
     * @param theY The new y-coordinate.
     */


}
