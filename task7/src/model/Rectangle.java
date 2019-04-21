package model;

/**
 * Created by User on 14.04.2019.
 */
public class Rectangle extends AbstractShape {
    private final double a;
    private final double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double square() {
        return a * b;
    }

    @Override
    public double perimeter() {
        return (a + b) * 2;
    }

}
