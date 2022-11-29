package lesson5.homework.factories;

import lesson5.homework.strategies.QuadraticStrategy;

public class QuadraticSolver implements CollisionSolver {

    @Override
    public QuadraticStrategy createStrategy() {
        return new QuadraticStrategy();
    }
}
