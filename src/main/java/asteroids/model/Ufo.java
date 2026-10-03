package asteroids.model;

import javafx.geometry.Point2D;

/**
 * Represents an enemy UFO in the game.
 */

public class Ufo extends GameObject {

    public Ufo(double x, double y) {
        super(new Point2D(x, y), 30, 15, 0, null);
    }

}
