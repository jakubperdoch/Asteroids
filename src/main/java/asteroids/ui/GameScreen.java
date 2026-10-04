package asteroids.ui;


import asteroids.input.KeyboardInput;
import asteroids.model.Asteroid;
import asteroids.model.AsteroidSize;
import asteroids.model.Ship;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GameScreen {

    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;
    private static final int SAFE_ZONE_RADIUS = 200;


    private final Scene scene;
    private final GraphicsContext gc;
    private final Image background;
    private final Ship ship;
    private final List<Asteroid> asteroids = new ArrayList<>();
    private final Random random = new Random();
    private final KeyboardInput keyboardInput;

    public GameScreen() {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        scene = new Scene(new StackPane(canvas), WIDTH, HEIGHT);
        keyboardInput = new KeyboardInput(scene);

        background = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/screens/game-screen.png")));
        ship = new Ship(WIDTH / 2, HEIGHT / 2);
        spawnAsteroids(5);

        AnimationTimer loop = new AnimationTimer() {
            private long lastTime = 0;

            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }

                double dt = (now - lastTime) / 1_000_000_000.0;
                lastTime = now;

                update(dt);
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
            double direction = random.nextDouble() * 360;
            int imageNumber = random.nextInt(1, 9);

            AsteroidSize[] sizes = AsteroidSize.values();
            AsteroidSize size = sizes[random.nextInt(sizes.length)];
            double rotationSpeed = random.nextDouble(-30, 30);

            if (Math.sqrt(Math.pow(x - ship.getX(), 2) + Math.pow(y - ship.getY(), 2)) < SAFE_ZONE_RADIUS) {
                continue;
            }

            asteroids.add(new Asteroid(x, y, size, imageNumber, direction, rotationSpeed));
        }
    }

    private void update(double dt) {
        ship.handleInput(keyboardInput);
        ship.update(dt);
        ship.wrap(WIDTH, HEIGHT);

        for (Asteroid asteroid : asteroids) {
            if (asteroid.getBoundingBox().intersects(ship.getBoundingBox())) {
                ship.takeDamage();
                if (!ship.isDestroyed()) {
                    ship.pushAwayFrom(asteroid);
                }
            }
            asteroid.update(dt);
            asteroid.wrap(WIDTH, HEIGHT);
        }
    }

    private void render() {
        gc.drawImage(background, 0, 0, WIDTH, HEIGHT);
        renderUI();
        for (Asteroid asteroid : asteroids) {
            asteroid.draw(gc);
        }
        ship.draw(gc);
    }

    private void renderUI() {
        gc.save();
        gc.setFill(javafx.scene.paint.Color.WHITE);
        gc.setFont(new Font("Arial", 15));
        gc.fillText("Health: " + ship.getHealth(), scene.getWidth() - 100, 30);
        gc.restore();
    }

}
