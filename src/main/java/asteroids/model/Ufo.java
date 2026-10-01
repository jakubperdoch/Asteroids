package asteroids.model;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents an enemy UFO in the game.
 */

public class Ufo extends GameObject {
    
    public Ufo(double x, double y) {
        super(x, y, 30, 15, 0, null);
    }

    @Override
    public void draw(GraphicsContext gc) {

    }
}
