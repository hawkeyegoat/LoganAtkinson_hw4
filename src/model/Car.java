package model;

import java.util.Map;

public class Car extends AbstractVehicle{
    protected static final int DEATH_TIME = 15;
    public Car(int theX, int theY, Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
    }

    /**
     * Returns whether or not this object may move onto the given type of
     * terrain, when the street lights are the given color.
     *
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether or not this object may move onto the given type of
     * terrain when the street lights are the given color.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return (theTerrain == Terrain.STREET //&& theLight != Light.RED)
          || (theTerrain == Terrain.CROSSWALK)
                || (theTerrain == Terrain.LIGHT && theLight != Light.RED));
        // && theLight != Light.RED);//|| theTerrain == Terrain.LIGHT;
    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        /*return getStraightLeftRightStream().
                filter(x -> isValidTerrain(theNeighbors.get(x))).
                sorted(SHUFFLE).findFirst().orElse(getDirection().reverse());*/
        if(isValidTerrain(theNeighbors.get(getDirection()))) {
            return getDirection();
        }
        else if (isValidTerrain(theNeighbors.get(getDirection().left()))) {
            return getDirection().left();
        }
        else if (isValidTerrain(theNeighbors.get(getDirection().right()))) {
            return getDirection().right();
        }
        else {
            return getDirection().reverse();
        }
    }
    private boolean isValidTerrain (final Terrain theTerrain) {
        return theTerrain == Terrain.STREET || theTerrain == Terrain.CROSSWALK || theTerrain == Terrain.LIGHT;
    }
}
