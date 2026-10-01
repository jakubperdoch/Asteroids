package asteroids.model;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a bullet in the game.
 */

public class Bullet extends GameObject {

    public Bullet(double x, double y) {
        super(x, y, 5, 5, 0, null);
    }

    @Override
    public void draw(GraphicsContext gc) {
    }
}
