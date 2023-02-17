package model;

public class Taxi extends Car {
    private int myCount = 0;
    public Taxi(int theX, int theY, Direction theDirection) {
        super(theX, theY, theDirection);
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if (theTerrain == Terrain.LIGHT && theLight == Light.RED) {
            myCount++;
        }
        else if (theLight == Light.GREEN) {
            myCount = 0;
        }
        return (theTerrain == Terrain.STREET //&& theLight != Light.RED)
                || (theTerrain == Terrain.CROSSWALK)
                || ((theTerrain == Terrain.LIGHT && theLight != Light.RED) || myCount == 3));
    }
}
