package com.dmirosh.hw3.resolvers;

public class LinearCollisionResolver implements CollisionResolver {
    private int step;

    private final int stepMultiplier;


    public LinearCollisionResolver(int stepMultiplier) {
        this.stepMultiplier = stepMultiplier;
        this.step = 1;
    }

    @Override
    public int nextIndex(int initialIndex, int maxSize) {
        int nextIndex = (initialIndex + step * stepMultiplier) % maxSize;
        step++;
        return nextIndex;
    }
}
