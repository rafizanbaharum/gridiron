package net.canang.gridiron.web.model;

/**
 * @author rafizan.baharum
 * @since 12/4/13
 */
public class CoordinateModel {

    private double x;
    private double y;

    public CoordinateModel(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
