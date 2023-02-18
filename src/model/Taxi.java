/**
 * TCSS 305 - Road Rage.
 */
package model;

/**
 * Taxi class.
 * @author Logan Atkinson
 * @version 2/17/23
 */
public class Taxi extends Car {
    /**
     * Constant to store the count of how long
     * the taxi has been waiting at a crosswalk
     * with a red light.
     */
    private int myCount;
    /**
     * Constant to store how long the taxi should
     * wait at a red light.
     */
    private static final int TIME = 3;
    /**
     * Taxi constructor.
     * @param theX the x value to pass our constructor
     * @param theY the y value to pass our constructor
     * @param theDirection the direction to pass our constructor
     */
    public Taxi(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection);
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
            myCount++;
        } else if (theLight == Light.GREEN) {
            myCount = 0;
        }
        return theTerrain == Terrain.STREET //&& theLight != Light.RED)
                || theTerrain == Terrain.CROSSWALK && theLight != Light.RED
                || theTerrain == Terrain.CROSSWALK && myCount == TIME
                || theTerrain == Terrain.LIGHT && theLight != Light.RED;
    }
}
