package com.dmirosh.hw3.strategies;

import com.dmirosh.hw3.resolvers.CollisionResolver;
import com.dmirosh.hw3.resolvers.LinearCollisionResolver;

public class LinearCollisionResolvingFactory implements CollisionResolvingFactory {
    int step;

    public LinearCollisionResolvingFactory(int step) {
        this.step = step;
    }

    @Override
    public CollisionResolver createResolver() {
        return new LinearCollisionResolver(step);
    }
}
