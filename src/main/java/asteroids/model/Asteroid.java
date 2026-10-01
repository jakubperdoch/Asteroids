package asteroids.model;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents an asteroid in the game.
 */
public class Asteroid extends GameObject {

    public Asteroid(double x, double y) {
        super(x, y, 50, 50, 0, null);
    }

    @Override
    public void draw(GraphicsContext gc) {
    }
}
