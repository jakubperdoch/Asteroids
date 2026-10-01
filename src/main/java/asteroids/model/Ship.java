package asteroids.model;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a ship in the game.
 */

public class Ship extends GameObject {

    public Ship(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(GraphicsContext gc) {
    }
}
