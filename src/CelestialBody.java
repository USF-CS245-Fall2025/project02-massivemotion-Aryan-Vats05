/**
 * CelestialBody class represents a celestial object (star or comet)
 * in the simulation with position, velocity, size, and type.
 */
public class CelestialBody {
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    private int size;
    private boolean isStar;

    /**
     * Constructor for CelestialBody
     * @param x initial x position
     * @param y initial y position
     * @param velocityX velocity in x direction
     * @param velocityY velocity in y direction
     * @param size radius of the body
     * @param isStar true if star, false if comet
     */
    public CelestialBody(double x, double y, double velocityX, double velocityY, int size, boolean isStar) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.size = size;
        this.isStar = isStar;
    }

    /**
     * Updates the position of the celestial body based on velocity
     */
    public void updatePosition() {
        x += velocityX;
        y += velocityY;
    }

    /**
     * Checks if the body is outside the canvas bounds
     * @param width canvas width
     * @param height canvas height
     * @return true if outside bounds, false otherwise
     */
    public boolean isOutOfBounds(int width, int height) {
        return x < -size || x > width + size || y < -size || y > height + size;
    }

    // Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public boolean isStar() {
        return isStar;
    }

    // Setters (if needed for future extensions)
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
}