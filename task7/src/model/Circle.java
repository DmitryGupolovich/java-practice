package model;

import service.Service;

/**
 * Created by User on 14.04.2019.
 */
public class Circle extends AbstractShape {
    private final double radius;
    private Service service;
    public Circle(Service service, String radius) {
        this.service=service;
        this.radius = this.service.checkPositiveValue(radius);
    }

    @Override
    public double square() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "square= " + square() +".2f"+
                ", perimeter=" + perimeter();
    }
}
