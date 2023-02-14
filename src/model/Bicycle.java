package model;

import java.util.Map;

public class Bicycle extends AbstractVehicle {
    private final static int DEATH_TIME = 35;

    public Bicycle(int theX, int theY, Direction theDirection) {
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
        return theTerrain == Terrain.TRAIL || (theTerrain == Terrain.STREET)
                //&& theLight == Light.GREEN)
                || (theTerrain == Terrain.CROSSWALK)
                || (theTerrain == Terrain.LIGHT && theLight == Light.GREEN);
                //&& theLight == Light.GREEN);
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
        if(theNeighbors.get(getDirection()) == Terrain.TRAIL) {
            return getDirection();
        }
        else if (theNeighbors.get(getDirection().left()) == Terrain.TRAIL) {
            return getDirection().left();
        }
        else if (theNeighbors.get(getDirection().right()) == Terrain.TRAIL) {
            return getDirection().right();
        }

        else {
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
            //return getDirection().reverse();
        }
    }

    private boolean isValidTerrain (final Terrain theTerrain) {
        return theTerrain == Terrain.TRAIL || theTerrain == Terrain.STREET || theTerrain == Terrain.CROSSWALK || theTerrain == Terrain.LIGHT;
    }
}
