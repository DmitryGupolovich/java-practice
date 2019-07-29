package model;

public interface IQueue<T> {

    T peek();

    T pop();

    void add(T elem);
}
