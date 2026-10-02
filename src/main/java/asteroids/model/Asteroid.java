package asteroids.model;

import javafx.scene.image.Image;

import java.util.Objects;
import java.util.Random;

/**
 * Represents an asteroid in the game.
 */
public class Asteroid extends GameObject {

    private static final String IMAGE_PATH = "/images/asteroids/asteroid_";

    private static final Random random = new Random();
    private final AsteroidSize size;

    public Asteroid(double x, double y, AsteroidSize size) {
        super(x, y, size.getRadius(), size.getRadius(),
                0, new Image(Objects.requireNonNull(Asteroid.class.getResourceAsStream(IMAGE_PATH + random.nextInt(1, 8) + ".png"))));
        this.size = size;
    }

    public AsteroidSize getSize() {
        return size;
    }
}
