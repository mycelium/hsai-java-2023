package lesson5.homework.factories;

import lesson5.homework.strategies.ProbingStrategy;

public interface CollisionSolver {

    ProbingStrategy createStrategy();
}
