package model;

/**
 * Created by User on 14.04.2019.
 */
public abstract class AbstractShape {

    public abstract double square();

    public abstract double perimeter();

    @Override
    public String toString() {
        return "square=" + square() +
                ", perimeter=" + perimeter();
    }
}
