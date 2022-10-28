package com.dmirosh.hw3;

import com.dmirosh.hw3.strategies.CollisionResolvingFactory;

import java.util.Optional;

public class HashMap {
    private record Element(String key, Point value) {

    }
    private final Element[] elements;

    private final int maxSize;

    private final CollisionResolvingFactory collisionResolvingStrategy;

    public HashMap(int maxSize, CollisionResolvingFactory collisionResolvingFactory) {
        this.maxSize = maxSize;
        this.collisionResolvingStrategy = collisionResolvingFactory;
        this.elements = new Element[maxSize];
    }

    public Point getOrElse(String k, Point fallback) {
        return Optional.ofNullable(get(k))
                .orElse(fallback);
    }
    public Optional<Point> getSafe(String k) {
        return Optional.ofNullable(get(k));
    }
    public boolean contains(String k) {
        return get(k) != null;
    }

    public Point get(String k) {
        var collisionResolver = collisionResolvingStrategy.createResolver();
        int index = hash(k);
        int step = 0;
        while(elements[index] != null && step < maxSize) {
            step++;
            if (elements[index].key.equals(k)) {
                return elements[index].value;
            } else {
                index = collisionResolver.nextIndex(index, maxSize);
            }
        }
        return null;
    }

    void put(String k, Point v) {
        var collisionResolver = collisionResolvingStrategy.createResolver();
        int index = hash(k);
        int step = 0;
        while (elements[index] != null && !elements[index].key.equals(k) && step < maxSize) {
            step++;
            index = collisionResolver.nextIndex(index, maxSize);
        }
        if (step < maxSize) {
            elements[index] = new HashMap.Element(k, v);
        } else {
            throw new IllegalStateException("table is full");
        }
    }

    public Optional<Point> remove(String k) {
        if (!contains(k)) {
            return Optional.empty();
        }
        var collisionResolver = collisionResolvingStrategy.createResolver();
        int index = hash(k);
        while (!elements[index].key.equals(k)) {
            index = collisionResolver.nextIndex(index, maxSize);
        }
        var found = elements[index].value;
        elements[index] = null;
        int prevIndex = index;
        index = collisionResolver.nextIndex(index, maxSize);
        while (elements[index] != null && elements[index].key.hashCode() == k.hashCode()) {
            elements[prevIndex] = elements[index];
            elements[index] = null;
            prevIndex = index;
            index = collisionResolver.nextIndex(index, maxSize);
        }
        return Optional.of(found);
    }

    private int hash(String k) {
        return k.hashCode() % maxSize;
    }
}
