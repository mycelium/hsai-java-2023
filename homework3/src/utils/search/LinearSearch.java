package utils.search;

import utils.hashUtils.MyHashElement;

import java.util.Objects;

public class LinearSearch extends MySearch{

    int stepMultiplier;

    public LinearSearch(int val){
        stepMultiplier = val;
    }

    public int nextIndex(int initialIndex, int step) {
        return initialIndex + stepMultiplier;
    }
}
