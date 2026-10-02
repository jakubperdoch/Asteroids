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
    private static final int MAX_HEALTH = 4;
    private static final double IMMUNITY_DURATION = 2.0;
    private static final double KNOCKBACK_FORCE = 250;
    private static final double THRUST = 300;

    private double immunityTimer = 0;
    private int rotationDirection = 0; // -1 for left, 1 for right, 0 for no rotation
    private boolean movingForward = false;
    private int health = MAX_HEALTH;

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
        if (immunityTimer > 0) {
            immunityTimer -= dt;
        }
        angle += rotationDirection * ROTATION_SPEED * dt;
        if (movingForward) {
            double radians = Math.toRadians(angle);
            velocityX += Math.cos(radians) * THRUST * dt;
            velocityY += Math.sin(radians) * THRUST * dt;

            double speed = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
            if (speed > MAX_SPEED) {
                velocityX = velocityX / speed * MAX_SPEED;
                velocityY = velocityY / speed * MAX_SPEED;
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
            if (immunityTimer > 0) {
                gc.setGlobalAlpha((int) (immunityTimer * 5) % 2 == 0 ? 0.3 : 0.8);
            }
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

    public void updateHealthImage(int health) {
        String imagePath = switch (health) {
            case 4 -> "/images/ship/MainShip-FullHealth.png";
            case 3 -> "/images/ship/MainShip-SlightDamage.png";
            case 2 -> "/images/ship/MainShip-Damaged.png";
            case 1 -> "/images/ship/MainShip-VeryDamaged.png";
            default -> "/images/ship/MainShip-FullHealth.png";
        };
        this.image = new Image(Objects.requireNonNull(Ship.class.getResourceAsStream(imagePath)));
    }

    public void takeDamage() {
        if (immunityTimer > 0) {
            return;
        }

        health--;
        immunityTimer = IMMUNITY_DURATION;

        if (health > 0) {
            updateHealthImage(health);
        }
        takeKnockBack(KNOCKBACK_FORCE, getDirection() + 180);
    }
}
