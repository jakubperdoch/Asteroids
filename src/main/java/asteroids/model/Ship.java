package asteroids.model;

import asteroids.input.KeyboardInput;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.Objects;


/**
 * Represents a ship in the game.
 */

public class Ship extends GameObject {

    private static final double SIZE = 70;
    private static final double ROTATION_SPEED = 200;
    private static final double MAX_SPEED = 500;
    private static final int MAX_HEALTH = 4;
    private static final double IMMUNITY_DURATION = 2.0;
    private static final double THRUST = 300;
    private static final String SHIP_FULL_HEALTH_IMAGE_PATH = "/images/ship/MainShip-FullHealth.png";
    private static final String SHIP_SLIGHT_DAMAGE_IMAGE_PATH = "/images/ship/MainShip-SlightDamage.png";
    private static final String SHIP_DAMAGED_IMAGE_PATH = "/images/ship/MainShip-Damaged.png";
    private static final String SHIP_VERY_DAMAGED_IMAGE_PATH = "/images/ship/MainShip-VeryDamaged.png";
    private static final String SHIP_EXPLOSION_IMAGE_PATH = "/images/explosions/ship_explosion.gif";
    private static final String SHIP_EXHAUST_IMAGE_PATH = "/images/exhaust.png";
    private static final double EXPLOSION_DURATION = 1.0;
    private int health = MAX_HEALTH;
    private double immunityTimer = 0;
    private int rotationDirection = 0;
    private double explosionTimer = 0;
    private boolean exploding = false;
    private boolean movingForward = false;

    public Ship(double x, double y) {
        super(new Point2D(x, y), SIZE, SIZE, -90,
                new Image(Objects.requireNonNull(Ship.class.getResourceAsStream(SHIP_FULL_HEALTH_IMAGE_PATH))));
    }

    public void handleInput(KeyboardInput input) {
        rotationDirection = 0;
        if (input.isPressed(KeyCode.LEFT)) {
            rotationDirection -= 1;
        }
        if (input.isPressed(KeyCode.RIGHT)) {
            rotationDirection += 1;
        }

        movingForward = input.isPressed(KeyCode.UP);
    }

    @Override
    public void update(double dt) {
        if (exploding) {
            explosionTimer -= dt;
            if (explosionTimer <= 0) {
                exploding = false;
            }
        }
        if (immunityTimer > 0) {
            immunityTimer -= dt;
        }
        angle += rotationDirection * ROTATION_SPEED * dt;
        if (movingForward) {
            double radians = Math.toRadians(angle);
            velocity = velocity.add(Math.cos(radians) * THRUST * dt, Math.sin(radians) * THRUST * dt);

            double speed = velocity.magnitude();
            if (speed > MAX_SPEED) {
                velocity = velocity.normalize().multiply(MAX_SPEED);
            }
        } else {
            velocity = velocity.multiply(0.99);
        }
        super.update(dt);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        if (movingForward) {
            gc.save();
            gc.translate(position.getX(), position.getY());
            gc.rotate(getDrawAngle());
            gc.drawImage(new Image(Objects.requireNonNull(Ship.class.getResourceAsStream(SHIP_EXHAUST_IMAGE_PATH))),
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
            case 3 -> SHIP_SLIGHT_DAMAGE_IMAGE_PATH;
            case 2 -> SHIP_DAMAGED_IMAGE_PATH;
            case 1 -> SHIP_VERY_DAMAGED_IMAGE_PATH;
            default -> SHIP_FULL_HEALTH_IMAGE_PATH;
        };
        this.image = new Image(Objects.requireNonNull(Ship.class.getResourceAsStream(imagePath)));
    }

    public void takeDamage() {
        if (immunityTimer > 0 || exploding) {
            return;
        }

        health--;
        immunityTimer = IMMUNITY_DURATION;

        if (health > 0) {
            updateHealthImage(health);
        } else {
            exploding = true;
            explosionTimer = EXPLOSION_DURATION;
            velocity = Point2D.ZERO;
            image = new Image(Objects.requireNonNull(Ship.class.getResourceAsStream(SHIP_EXPLOSION_IMAGE_PATH)));
        }

    }

    public String getHealth() {
        return String.valueOf(health);
    }

    public boolean isDestroyed() {
        return exploding && explosionTimer <= 0;
    }
}
