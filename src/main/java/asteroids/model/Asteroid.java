package asteroids.model;

import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Represents an asteroid in the game.
 */
public class Asteroid extends GameObject {

    private static final String IMAGE_PATH = "/images/asteroids/asteroid_";

    private final AsteroidSize size;

    public Asteroid(double x, double y, AsteroidSize size, int imageNumber) {
        super(x, y, size.getRadius() * 2, size.getRadius() * 2,
                0, new Image(Objects.requireNonNull(Asteroid.class.getResourceAsStream(IMAGE_PATH + imageNumber + ".png"))));
        this.size = size;
    }

    public AsteroidSize getSize() {
        return size;
    }
}
