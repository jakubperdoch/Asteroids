package asteroids.model;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents an enemy UFO in the game.
 */

public class Ufo extends GameObject {
    protected double x;
    protected double y;

    public Ufo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(GraphicsContext gc) {

    }
}
