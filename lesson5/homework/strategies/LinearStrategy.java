package lesson5.homework.strategies;

public class LinearStrategy implements ProbingStrategy {

    private final int multiplier;
    private int step;

    public LinearStrategy(int multiplier) {
        this.multiplier = multiplier;
        this.step = 1;
    }

    @Override
    public int getNewCapacity(int oldCapacity) {
        return oldCapacity * 2;
    }

    @Override
    public int nextIndex(int previousIndex, int capacity) {
        int nextInd = (previousIndex + step * multiplier) % capacity;
        step++;
        return nextInd;
    }
}
