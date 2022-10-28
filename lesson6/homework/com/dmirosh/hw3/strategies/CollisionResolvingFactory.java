package com.dmirosh.hw3.strategies;

import com.dmirosh.hw3.resolvers.CollisionResolver;

public interface CollisionResolvingFactory {

    CollisionResolver createResolver();
}
