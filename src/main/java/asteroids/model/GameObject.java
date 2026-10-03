package asteroids.model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents a generic game object in the game.
 */

public abstract class GameObject {
    private static final double BOUNCE_SPEED = 200;

    protected Point2D position;
    protected double width;
    protected double height;
    protected double angle;
    protected Point2D velocity = Point2D.ZERO;
    protected Image image;

    protected GameObject(Point2D position, double width, double height, double angle, Image image) {
        this.position = new Point2D(position.getX(), position.getY());
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.image = image;
    }

    public void update(double dt) {
        this.position = this.position.add(velocity.getX() * dt, velocity.getY() * dt);
    }

    public void draw(GraphicsContext gc) {
        gc.save();
        gc.translate(position.getX(), position.getY());
        gc.rotate(getDrawAngle());
        gc.drawImage(image, -width / 2, -height / 2, width, height);
        gc.restore();
    }

    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(position.getX(), position.getY(), image.getWidth(), image.getHeight());
    }

    public void pushAwayFrom(GameObject other) {
        double dx = position.getX() - other.position.getX();
        double dy = position.getY() - other.position.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        double minDistance = width / 2 + other.width / 2;

        if (distance == 0 || distance >= minDistance) {
            return;
        }

        position = new Point2D(other.position.getX() + dx / distance * minDistance, other.position.getY() + dy / distance * minDistance);
        velocity = new Point2D(dx / distance * BOUNCE_SPEED, dy / distance * BOUNCE_SPEED);
    }

    public void wrap(double screenWidth, double screenHeight) {
        double halfWidth = width / 2;
        double halfHeight = height / 2;

        if (position.getX() < -halfWidth) {
            position = new Point2D(screenWidth + halfWidth, position.getY());
        } else if (position.getX() > screenWidth + halfWidth) {
            position = new Point2D(-halfWidth, position.getY());
        }

        if (position.getY() < -halfHeight) {
            position = new Point2D(position.getX(), screenHeight + halfHeight);
        } else if (position.getY() > screenHeight + halfHeight) {
            position = new Point2D(position.getX(), -halfHeight);
        }
    }

    protected double getDrawAngle() {
        return angle;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }
}
