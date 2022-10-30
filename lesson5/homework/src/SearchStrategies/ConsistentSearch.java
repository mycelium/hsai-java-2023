package lesson5.homework.src.SearchStrategies;

import java.util.Objects;
import lesson5.homework.src.HashMap.HashMapElement;

public class ConsistentSearch implements SearchStrategy{
    public int setHash(int hash) {
        return hash + 1;
    }
}