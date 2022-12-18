package lesson5.homework.strategies;

public class QuadraticStrategy implements ProbingStrategy {
    private int step;

    public QuadraticStrategy() {
        this.step = 1;
    }

    @Override
    public int getNewCapacity(int oldCapacity) {
        // new capacity should be prime number ~ two times bigger than old capacity
        return oldCapacity * 3;
    }

    @Override
    public int nextIndex(int previousIndex, int capacity) {
        int nextInd = (previousIndex + step * step) % capacity;
        step++;
        return nextInd;
    }
}
