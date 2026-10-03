package asteroids.model;

import javafx.geometry.Point2D;

/**
 * Represents a bullet in the game.
 */

public class Bullet extends GameObject {

    public Bullet(double x, double y) {
        super(new Point2D(x, y), 5, 5, 0, null);
    }

}
