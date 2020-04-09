package Explorer.Geometry;
import Explorer.Utils.Require;

/**
 * Represents an euclidean distance.
 */
public final class Distance {

    private double distance;
    private Point pointA;
    private Point pointB;

    public Distance(Point pointA, Point pointB) {
        this(Math.sqrt(
            Math.pow(pointA.getX() - pointB.getX(), 2) +
            Math.pow(pointA.getY() - pointB.getY(), 2)
        ));
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public Distance(double distance) {
        Require.realNumber(distance, "Distance must be real!");
        Require.notNegative(distance, "Distance can not be negative!");
        this.distance = distance;
    }

    public double getValue() {
        return distance;
    }

    public Point getPointA() {
        return pointA;
    }

    public Point getPointB() {
        return pointB;
    }
}
