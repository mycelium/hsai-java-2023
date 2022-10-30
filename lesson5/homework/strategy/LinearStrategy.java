package lesson5.homework.strategy;

public class LinearStrategy implements SearchStrategy {
    private final int step;

    public LinearStrategy(int step) {
        this.step = step;
    }

    public int searchStep(boolean updateStep) {
        return step;
    }
}