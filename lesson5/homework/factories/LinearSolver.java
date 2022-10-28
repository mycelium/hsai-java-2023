package lesson5.homework.factories;

import lesson5.homework.strategies.LinearStrategy;

public class LinearSolver implements CollisionSolver {

    private final int step;

    public LinearSolver(int step) {
        this.step = step;
    }

    @Override
    public LinearStrategy createStrategy() {
        return new LinearStrategy(step);
    }
}
