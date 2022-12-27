package lesson5.homework.search;

public class QuadraticSearch implements SequentialSearch{
    private int step;

    @Override
    public SequentialSearch createSearch() { return new QuadraticSearch(); }

    public QuadraticSearch() {
        this.step = 1;
    }

    @Override
    public int getNewCapacity(int oldCapacity) {
        return oldCapacity * 3;
    }

    @Override
    public int nextIndex(int previousIndex, int capacity) {
        int nextInd = (previousIndex + step * step) % capacity;
        step++;
        return nextInd;
    }
}
