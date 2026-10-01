package asteroids.model;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a generic game object in the game.
 */

abstract class GameObject {
    protected double x;
    protected double y;

    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public abstract void draw(GraphicsContext gc);
}
