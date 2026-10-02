package asteroids.model;

import javafx.scene.image.Image;

import java.util.Objects;


/**
 * Represents a ship in the game.
 */

public class Ship extends GameObject {
    
    private static final double SIZE = 60;
    private static final String IMAGE_PATH = "/images/ship/MainShip-FullHealth.png";

    public Ship(double x, double y) {
        super(x, y, SIZE, SIZE, 0,
                new Image(Objects.requireNonNull(Ship.class.getResourceAsStream(IMAGE_PATH))));
    }
}
