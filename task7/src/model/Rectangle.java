package model;

import service.Service;

/**
 * Created by User on 14.04.2019.
 */
public class Rectangle extends AbstractShape {
    private final double a;
    private final double b;
    private Service service;

    public Rectangle(Service service, String a, String b) {
        this.service=service;
        this.a = this.service.checkPositiveValue(a);
        this.b = this.service.checkPositiveValue(b);
    }

    @Override
    public double square() {
        return a*b;
    }

    @Override
    public double perimeter() {
        return (a+b)*2;
    }

    @Override
    public String toString() {
        return "square=" + square() +
                ", perimeter=" + perimeter();
    }
}
