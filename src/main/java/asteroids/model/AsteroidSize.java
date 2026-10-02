package asteroids.model;

public enum AsteroidSize {
    LARGE(40),
    MEDIUM(25),
    SMALL(12);

    private final double radius;

    AsteroidSize(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

}
