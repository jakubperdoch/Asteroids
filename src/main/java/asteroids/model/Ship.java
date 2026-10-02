package asteroids.model;

import asteroids.input.KeyboardInput;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.Objects;


/**
 * Represents a ship in the game.
 */

public class Ship extends GameObject {

    private static final double SIZE = 70;
    private static final double ROTATION_SPEED = 5;
    private static final double MAX_SPEED = 500;
    private static final double JETS = 200;
    private boolean movingForward = false;

    private int rotationDirection = 0; // -1 for left, 1 for right, 0 for no rotation

    public Ship(double x, double y) {
        super(x, y, SIZE, SIZE, -90,
                new Image(Objects.requireNonNull(Ship.class.getResourceAsStream("/images/ship/MainShip-FullHealth.png"))));
    }

    public void handleInput(KeyboardInput input) {
        if (input.isPressed(KeyCode.LEFT)) {
            rotationDirection -= 1;
        } else if (input.isPressed(KeyCode.RIGHT)) {
            rotationDirection += 1;
        } else {
            rotationDirection = 0;
        }

        movingForward = input.isPressed(KeyCode.UP);
    }

    @Override
    public void update(double dt) {
        angle += rotationDirection * ROTATION_SPEED * dt;
        if (movingForward) {
            double radians = Math.toRadians(angle);
            if (Math.abs(velocityX) < MAX_SPEED) {
                velocityX += Math.cos(radians) * JETS * dt;
            }
            if (Math.abs(velocityY) < MAX_SPEED) {
                velocityY += Math.sin(radians) * JETS * dt;
            }
        } else {
            velocityX *= 0.99;
            velocityY *= 0.99;
        }
        super.update(dt);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        if (movingForward) {
            gc.save();
            gc.translate(x, y);
            gc.rotate(getDrawAngle());
            gc.drawImage(new Image(Objects.requireNonNull(Ship.class.getResourceAsStream("/images/exhaust.png"))),
                    -width / 2, -height / 4, width, height);
            gc.restore();
        }
    }

    @Override
    protected double getDrawAngle() {
        return angle + 90;
    }
}
