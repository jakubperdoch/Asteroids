package asteroids.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents a generic game object in the game.
 */

public abstract class GameObject {
    private static final double BOUNCE_SPEED = 200;

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
        gc.rotate(getDrawAngle());
        gc.drawImage(image, -width / 2, -height / 2, width, height);
        gc.restore();
    }

    public boolean collidesWith(GameObject other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < (this.width / 2 + other.width / 2);
    }

    public void pushAwayFrom(GameObject other) {
        double dx = x - other.x;
        double dy = y - other.y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        double minDistance = width / 2 + other.width / 2;

        if (distance == 0 || distance >= minDistance) {
            return;
        }

        x = other.x + dx / distance * minDistance;
        y = other.y + dy / distance * minDistance;
        velocityX = dx / distance * BOUNCE_SPEED;
        velocityY = dy / distance * BOUNCE_SPEED;
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

    protected double getDrawAngle() {
        return angle;
    }

    public double getDirection() {
        return angle;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
