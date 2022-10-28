package com.dmirosh.hw3.builder;

import com.dmirosh.hw3.HashMap;
import com.dmirosh.hw3.strategies.LinearCollisionResolvingFactory;
import com.dmirosh.hw3.strategies.QuadraticCollisionResolvingFactory;

public class HashMapBuilder {
    public static class FirstStep {
        private int maxSize;

        public FirstStep(int maxSize) {
            this.maxSize = maxSize;
        }

        public FirstStep withMaxSize(int maxSize) {
            this.maxSize = maxSize;
            return this;
        }

        public QuadraticStep withQuadraticStrategy() {
            return new QuadraticStep(maxSize);
        }

        public LinearStep2 withSequentialStrategy() {
            return new LinearStep2(maxSize, 1);
        }

        public LinearStep withLinearStrategy() {
            return new LinearStep(maxSize);
        }
    }

    public  static class QuadraticStep {
        private int maxSize;

        public QuadraticStep(int maxSize) {
            this.maxSize = maxSize;
        }

        public HashMap build() {
            return new HashMap(maxSize, new QuadraticCollisionResolvingFactory());
        }
    }

    public  static class LinearStep {
        private int maxSize;

        public LinearStep(int maxSize) {
            this.maxSize = maxSize;
        }

        public LinearStep2 withStep(int step) {
            return new LinearStep2(maxSize, step);
        }
    }

    public  static class LinearStep2 {
        int step;
        int maxSize;
        public LinearStep2(int maxSize, int step) {
            this.maxSize = maxSize;
            this.step = step;
        }

        public HashMap build() {
            return new HashMap(maxSize, new LinearCollisionResolvingFactory(step));
        }
    }

    public static FirstStep newBuilder() {
        return new FirstStep(10);
    }
}
