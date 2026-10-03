package asteroids.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Represents an asteroid in the game.
 */
public class Asteroid extends GameObject {

    private static final String IMAGE_PATH = "/images/asteroids/asteroid_";

    private final AsteroidSize size;
    private final double rotationSpeed;

    public Asteroid(double x, double y, AsteroidSize size, int imageNumber, double direction, double rotationSpeed) {
        super(new Point2D(x, y), size.getRadius() * 2, size.getRadius() * 2,
                0, new Image(Objects.requireNonNull(Asteroid.class.getResourceAsStream(IMAGE_PATH + imageNumber + ".png"))));
        this.size = size;
        this.rotationSpeed = rotationSpeed;

        double radians = Math.toRadians(direction);
        this.velocity = new Point2D(size.getSpeed() * Math.cos(radians), size.getSpeed() * Math.sin(radians));
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        angle += rotationSpeed * dt;
    }

    public AsteroidSize getSize() {
        return size;
    }
}
