package lesson5.homework.search;

public class LinearSearch implements SequentialSearch{
    private final int mlt;
    private int step;

    public LinearSearch(int mult) {
        this.mlt = mult;
        this.step = 1;
    }

    @Override
    public SequentialSearch createSearch() { return new LinearSearch(step);}

    public int getNewCapacity(int oldCapacity) {
        return oldCapacity * 2;
    }

    public int nextIndex(int previousIndex, int capacity) {
        int nextInd = (previousIndex + step * mlt) % capacity;
        step++;
        return nextInd;
    }
}