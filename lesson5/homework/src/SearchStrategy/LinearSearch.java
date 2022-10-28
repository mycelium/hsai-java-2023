package lesson5.homework.src.SearchStrategy;

public class LinearSearch extends SearchStrategy_Abstract {
    protected int newHash(int hash) {
        return hash + this.step;
    }
}
