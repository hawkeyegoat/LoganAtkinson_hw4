/*
 * TCSS 305 - Road Rage
 */
package model;

import java.util.Comparator;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Abstract class for our vehicle classes.
 * @author Logan Atkinson
 * @version 2/17/23
 */
public abstract class AbstractVehicle implements Vehicle {
    /**
     * Final constant to produce a random value.
     */
    protected static final Random RAND = new Random();
    /**
     * Final constant comparator to order in a random order.
     */
    protected static final Comparator<Direction> SHUFFLE =
            (theFirst, theSecond) -> RAND.nextInt();
    /**
     * Field to store the X value.
     */
    private int myX;
    /**
     * Field to store the Y value.
     */
    private int myY;
    /**
     * Field to store the direction value.
     */
    private Direction myDirection;
    /**
     * Field to store the initial X value.
     */
    private final int myInitialX;
    /**
     * Field to store the initial Y value.
     */
    private final int myInitialY;
    /**
     * Field to store the initial direction value.
     */
    private final Direction myInitialDir;
    /**
     * Field to store how long a vehicle should
     * stay dead for.
     */
    private final int myDeathTime;
    /**
     * Field to store how long the vehicle has been dead for.
     */
    private int myPokeCount;
    /**
     * Field to store whether the vehicle is dead.
     */
    private boolean myAlive;
    /**
     * Constructor.
     * @param theX The X value to pass our constructor
     * @param theY The Y value to pass our constructor
     * @param theDirection The Direction value to pass our constructor
     * @param theDeathTime How long the vehicle should stay dead for
     */
    protected AbstractVehicle(final int theX, final int theY, final Direction theDirection,
                           final int theDeathTime) {
        myInitialX = theX;
        myInitialY = theY;
        myInitialDir = theDirection;
        myDeathTime = theDeathTime;
        reset();
    }
    /**
     * Resets the given vehicle to its initial state.
     */
    public final void reset() {
        myX = myInitialX;
        myY = myInitialY;
        myDirection = myInitialDir;
        myPokeCount = 0;
        myAlive = true;
    }
    /**
     * Says whether the vehicle should die on hitting
     * another vehicle.
     */
    public void collide(final Vehicle theOther) {
        if (isAlive() && theOther.isAlive() && getDeathTime() > theOther.getDeathTime()) {
            myAlive = false;
        }
    }
    /**
     * Sets the vehicle's direction.
     * @param theDirection The new direction.
     */
    public void setDirection(final Direction theDirection) {
        myDirection = theDirection;
    }
    /**
     * Sets the vehicles x-coordinate.
     * @param theX The new x-coordinate.
     */
    public void setX(final int theX) {
        myX = theX;
    }
    /**
     * Sets the vehicles y-coordinate.
     * @param theY The new y-coordinate.
     */
    public void setY(final int theY) {
        myY = theY;
    }
    /**
     * Gets the vehicles current x-coordinate.
     * @return The x value of the vehicle
     */
    public int getX() {
        return myX;
    }
    /**
     * Gets the vehicles current y-coordinate.
     * @return The y value of the vehicle
     */

    public int getY() {
        return myY;
    }

    /**
     * Gets the vehicles current direction.
     * @return The direction value
     */
    public Direction getDirection() {
        return myDirection;
    }

    /**
     * Gets whether the vehicle is alive.
     * @return the myAlive boolean
     */
    public boolean isAlive() {
        return myAlive;
    }

    /**
     * Used to get the vehicles image files.
     * @return the toString value of the images file
     */
    public String getImageFileName() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName().toLowerCase(Locale.US));
        if (!isAlive()) {
            stringBuilder.append("_dead");
        }
        stringBuilder.append(".gif");
        return stringBuilder.toString();
    }

    /**
     * The toString builder for out vehicles.
     * @return the toString
     */
    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        if (isAlive()) {
            stringBuilder.append(getClass().getSimpleName());
        } else {
            stringBuilder.append("pokes: ");
            stringBuilder.append(myPokeCount);
        }
        return stringBuilder.toString();
    }
    /**
     * Is called each tick to see if a vehicle should revive yet.
     */
    public void poke() {
        //if(!isAlive()) {
        myPokeCount++;
        if (myPokeCount == getDeathTime()) {
            myDirection = Direction.random();
            myPokeCount = 0;
            myAlive = true;
        }
    }
    /**
     * Gets the death time value of our vehicle.
     * @return myDeathTime
     */
    public int getDeathTime() {
        return myDeathTime;
    }
    /**
     * Gets the direction stream.
     * @return Direction stream
     */
    protected Stream<Direction> getStraightLeftRightStream() {
        return Stream.of(getDirection(), getDirection().left(), getDirection().right());
    }
}
