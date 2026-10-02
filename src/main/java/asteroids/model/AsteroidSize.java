package asteroids.model;

public enum AsteroidSize {
    LARGE(55, 10, 40),
    MEDIUM(45, 20, 70),
    SMALL(30, 30, 110);

    private final double radius;
    private final int points;
    private final int speed;

    AsteroidSize(double radius, int points, int speed) {
        this.radius = radius;
        this.points = points;
        this.speed = speed;
    }

    public double getRadius() {
        return radius;
    }

    public int getPoints() {
        return points;
    }

    public int getSpeed() {
        return speed;
    }
}
