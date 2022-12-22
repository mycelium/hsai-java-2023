package lesson5.homework.search;

public interface SequentialSearch {
    SequentialSearch createSearch();
    int getNewCapacity(int oldCapacity);
    int nextIndex(int previousIndex, int capacity);
}
