package asteroids.model;

import asteroids.input.KeyboardInput;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.Objects;


/**
 * Represents a ship in the game.
 */

public class Ship extends GameObject {

    private static final String IMAGE_PATH = "/images/ship/MainShip-FullHealth.png";
    private static final double SIZE = 70;
    private static final double ROTATION_SPEED = 5;

    private int rotationDirection = 0; // -1 for left, 1 for right, 0 for no rotation

    public Ship(double x, double y) {
        super(x, y, SIZE, SIZE, 0,
                new Image(Objects.requireNonNull(Ship.class.getResourceAsStream(IMAGE_PATH))));
    }

    public void handleInput(KeyboardInput input) {
        if (input.isPressed(KeyCode.LEFT)) {
            rotationDirection -= 1;
        } else if (input.isPressed(KeyCode.RIGHT)) {
            rotationDirection += 1;
        } else {
            rotationDirection = 0;
        }
    }

    @Override
    public void update(double dt) {
        angle += rotationDirection * ROTATION_SPEED * dt;
        super.update(dt);
    }
}
