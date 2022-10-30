package lesson5.homework.strategies;

public interface ProbingStrategy {

    int getNewCapacity(int oldCapacity);

    int nextIndex(int previousIndex, int capacity);
}
