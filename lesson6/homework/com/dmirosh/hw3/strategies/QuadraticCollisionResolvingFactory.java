package com.dmirosh.hw3.strategies;

import com.dmirosh.hw3.resolvers.CollisionResolver;
import com.dmirosh.hw3.resolvers.QuadraticCollisionResolver;

public class QuadraticCollisionResolvingFactory implements CollisionResolvingFactory {

    @Override
    public CollisionResolver createResolver() {
        return new QuadraticCollisionResolver();
    }
}
