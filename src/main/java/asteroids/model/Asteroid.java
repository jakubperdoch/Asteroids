package asteroids.model;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents an asteroid in the game.
 */
public class Asteroid extends GameObject {

    public Asteroid(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(GraphicsContext gc) {
    }
}
