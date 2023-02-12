package model;

import java.util.Locale;
import java.util.stream.Stream;

public Abstract class AbstractVehicle implements Vehicle {
    //protected
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
            myPokeCount = myDeathTime;
        }
    }
    public String getImageFileName() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName().toLowerCase(Locale.US));
        if (!isAlive()) {
            sb.append("_dead");
        }
        return sb.toString();
    }
    public void poke() {
        //if(!isAlive()) {
            if (myPokeCount > 0) {
                myPokeCount--;
            }
            else {
                myAlive = true;
            }
       // }
    }
    public int getDeathTime() {
        return myDeathTime;
    }
    protected Stream<Direction> getStraightLeftRightStream() {

    }
}
