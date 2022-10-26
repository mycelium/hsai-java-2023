package lesson6.homework.src.Search;

import lesson6.homework.src.Helpers.HashMapElement;

public class LinearSearch extends AbstractSearch {
    protected int changedHash(int hash) {
        return hash + this.step;
    }
}
