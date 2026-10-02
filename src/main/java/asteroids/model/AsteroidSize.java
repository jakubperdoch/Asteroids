package asteroids.model;

public enum AsteroidSize {
    LARGE(55),
    MEDIUM(45),
    SMALL(30);

    private final double radius;

    AsteroidSize(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

}
