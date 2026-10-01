package asteroids.model;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a bullet in the game.
 */

public class Bullet extends GameObject {

    public Bullet(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(GraphicsContext gc) {
    }
}
