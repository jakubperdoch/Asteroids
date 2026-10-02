package asteroids.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents a generic game object in the game.
 */

public abstract class GameObject {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double angle;
    protected double velocityX;
    protected double velocityY;

    protected Image image;

    protected GameObject(double x, double y, double width, double height, double angle, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.image = image;
    }

    public void update(double dt) {
        this.x += velocityX * dt;
        this.y += velocityY * dt;
    }

    public void draw(GraphicsContext gc) {
        gc.save();
        gc.translate(x, y);
        gc.rotate(angle);
        gc.drawImage(image, -width / 2, -height / 2, width, height);
        gc.restore();
    }

    public void wrap(double screenWidth, double screenHeight) {
        double halfWidth = width / 2;
        double halfHeight = height / 2;

        if (x < -halfWidth) {
            x = screenWidth + halfWidth;
        } else if (x > screenWidth + halfWidth) {
            x = -halfWidth;
        }

        if (y < -halfHeight) {
            y = screenHeight + halfHeight;
        } else if (y > screenHeight + halfHeight) {
            y = -halfHeight;
        }
    }
}
