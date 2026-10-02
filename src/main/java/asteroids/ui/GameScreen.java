package asteroids.ui;


import asteroids.model.Asteroid;
import asteroids.model.AsteroidSize;
import asteroids.model.Ship;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GameScreen {

    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;
    private static final double SAFE_DISTANCE = 150;

    private final Scene scene;
    private final GraphicsContext gc;
    private final Image background;
    private final Ship ship;
    private final List<Asteroid> asteroids = new ArrayList<>();
    private final Random random = new Random();

    public GameScreen() {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        scene = new Scene(new StackPane(canvas), WIDTH, HEIGHT);

        background = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/screens/game-screen.png")));
        ship = new Ship(WIDTH / 2, HEIGHT / 2);
        spawnAsteroids(5);

        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                render();
            }
        };

        loop.start();
    }

    public Scene getScene() {
        return scene;
    }

    private void spawnAsteroids(int count) {
        while (asteroids.size() < count) {
            double x = random.nextDouble() * WIDTH;
            double y = random.nextDouble() * HEIGHT;
            int imageNumber = random.nextInt(1, 9);

            AsteroidSize[] sizes = AsteroidSize.values();
            AsteroidSize size = sizes[random.nextInt(sizes.length)];
            
            asteroids.add(new Asteroid(x, y, size, imageNumber));
        }
    }

    private void render() {
        gc.drawImage(background, 0, 0, WIDTH, HEIGHT);
        ship.draw(gc);
        for (Asteroid asteroid : asteroids) {
            asteroid.draw(gc);
        }
    }

}
