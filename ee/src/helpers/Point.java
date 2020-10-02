package helpers;

public class Point {

    private double x;
    private double y;
    private int r;

    public Point(double x, double y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public double getX ( ) {
        return x;
    }

    public double getY ( ) {
        return y;
    }

    public int getR ( ) {
        return r;
    }
}
