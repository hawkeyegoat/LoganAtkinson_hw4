package model;

import java.util.Map;
import java.util.Objects;

public class Human implements Vehicle {
    public Direction myDirection;
    public int myX;
    public int myY;
    public int initialX;
    public int initialY;
    public Direction initialDir;
    public int timer = 0;

    /**
     * Constructor
     */
    public Human(int theX, int theY, Direction theDir) {
        initialX = theX;
        initialY = theY;
        myX = theX;
        myY = theY;
        myDirection = Objects.requireNonNull(theDir);

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
        if (theLight == Light.RED) {
            return false;
        }
        if (theTerrain != Terrain.GRASS && theTerrain != Terrain.CROSSWALK) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        myDirection = Direction.random();
        return myDirection;
    }

    /**
     * Called when this Vehicle collides with the specified other Vehicle.
     *
     * @param theOther The other object.
     */
    @Override
    public void collide(Vehicle theOther) {
        //Do nothing
    }

    /**
     * Returns the number of updates between this vehicle's death and when it
     * should be revived.
     *
     * @return the number of updates.
     */
    @Override
    public int getDeathTime() {
        timer = 45;
        return 45;
    }
    @Override
    /**
     * Returns the file name of the image for this Vehicle object, such as
     * "car.gif".
     *
     * @return the file name.
     */
    public String getImageFileName() {
        return "Human.gif";
    }

    /**
     * Returns this Vehicle object's direction.
     *
     * @return the direction.
     */
    public Direction getDirection() {
        return myDirection;
    }

    /**
     * Returns this Vehicle object's x-coordinate.
     *
     * @return the x-coordinate.
     */
    public int getX() {
        return myX;
    }

    /**
     * Returns this Vehicle object's y-coordinate.
     *
     * @return the y-coordinate.
     */
    public int getY() {
        return myY;
    }

    /**
     * Returns whether this Vehicle object is alive and should move on the map.
     *
     * @return true if the object is alive, false otherwise.
     */
    public boolean isAlive() {
        if (timer > 0) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Called by the UI to notify a dead vehicle that 1 movement round has
     * passed, so that it will become 1 move closer to revival.
     */
    public void poke() {
        //Do nothing, cannot be dead;
        timer--;
    }

    /**
     * Moves this vehicle back to its original position.
     */
    public void reset() {
        myX = initialX;
        myY = initialY;
        myDirection = initialDir;
    }

    /**
     * Sets this object's facing direction to the given value.
     *
     * @param theDir The new direction.
     */
    public void setDirection(Direction theDir) {
        myDirection = theDir;
    }

    /**
     * Sets this object's x-coordinate to the given value.
     *
     * @param theX The new x-coordinate.
     */
    public void setX(int theX) {
        myX = theX;
    }

    /**
     * Sets this object's y-coordinate to the given value.
     *
     * @param theY The new y-coordinate.
     */
    public void setY(int theY) {
        myY = theY;
    }

}
