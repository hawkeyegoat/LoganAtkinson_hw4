package model;

import java.util.Comparator;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Stream;

public abstract class AbstractVehicle implements Vehicle {
    //protected
    protected static final Random RAND = new Random();

    protected static final Comparator<Direction> SHUFFLE =
            (theFirst, theSecond) -> RAND.nextInt();
    protected static final Comparator<Direction> CAR =
            (theFirst, theSecond) -> RAND.nextInt();

    private int myX;
    private int myY;
    private Direction myDirection;
    private final int myIntialX;
    private final int myInitialY;
    private Direction myInitialDirection;
    private final int myDeathTime;
    private int myPokeCount;
    private boolean myAlive;

    protected AbstractVehicle(final int theX, final int theY, final Direction theDirection,
                           final int theDeathTime) {
        myIntialX = theX;
        myInitialY = theY;
        myInitialDirection = theDirection;
        myDeathTime = theDeathTime;
        reset();
    }
    public final void reset() {
        myX = myIntialX;
        myY = myInitialY;
        myDirection = myInitialDirection;
        myPokeCount = 0;
        myAlive = true;
    }
    public void collide (final Vehicle theOther) {
        if (isAlive() && theOther.isAlive() && (getDeathTime() > theOther.getDeathTime())) {
            myAlive = false;
            //myPokeCount = myDeathTime;
        }
    }
    protected boolean isValidTerrain;
    public void setDirection(final Direction theDirection) { myDirection = theDirection; }

    public void setX(final int theX) { myX = theX; }

    public void setY(final int theY) { myY = theY; }

    public int getX() {
        return myX;
    }

    public int getY() {
        return myY;
    }

    public Direction getDirection() {
        return myDirection;
    }

    public boolean isAlive() {
        return myAlive;
    }

    public String getImageFileName() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName().toLowerCase(Locale.US));
        if (!isAlive()) {
            sb.append("_dead");
        }
        sb.append(".gif");
        return sb.toString();
    }
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (isAlive()) {
            sb.append(getClass().getSimpleName());
        } else {
            sb.append("pokes: ");
            sb.append(myPokeCount);
        }
        return sb.toString();
    }
    public void poke() {
        //if(!isAlive()) {
            myPokeCount++;
            if (myPokeCount == getDeathTime()) {
                myDirection = Direction.random();
                myPokeCount = 0;
                myAlive = true;
            }
       // }
    }
    protected boolean isNotReverse(final Direction theDir) {
        return theDir != getDirection().reverse();
    }
    public int getDeathTime() {
        return myDeathTime;
    }
    protected Stream<Direction> getStraightLeftRightStream() {
        return Stream.of(getDirection(), getDirection().left(), getDirection().right());
    }
}
