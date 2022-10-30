package lesson5.homework.src.SearchStrategies;

import java.util.Objects;
import lesson5.homework.src.HashMap.HashMapElement;

public class LinearSearch implements SearchStrategy{
    int step = 1;

    @Override
    public void setStep(int step) {
        this.step = step;
    }

    public int setHash(int hash) {
        return hash + this.step;
    }
}