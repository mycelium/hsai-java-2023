package com.dmirosh.hw3.resolvers;

public class QuadraticCollisionResolver implements CollisionResolver {

    private int step;

    public QuadraticCollisionResolver() {
        this.step = 1;
    }

    @Override
    public int nextIndex(int initialIndex, int maxSize) {
        var nextIndex = (initialIndex + step * step) % maxSize;
        step++;
        return nextIndex;
    }
}
