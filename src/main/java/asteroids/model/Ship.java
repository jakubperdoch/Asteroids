package asteroids.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;


/**
 * Represents a ship in the game.
 */

public class Ship extends GameObject {
    private final Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/ship.png")));

    public Ship(double x, double y) {
        super(x, y, 40, 20, 0, null);
    }

    @Override
    public void draw(GraphicsContext gc) {

    }
}
