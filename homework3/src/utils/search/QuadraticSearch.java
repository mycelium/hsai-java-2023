package utils.search;

import utils.hashUtils.MyHashElement;

public class QuadraticSearch extends MySearch{

    public int nextIndex(int initialIndex, int step) {
        return initialIndex + step*step;
    }

}
