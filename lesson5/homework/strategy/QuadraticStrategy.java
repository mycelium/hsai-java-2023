package lesson5.homework.strategy;

public class QuadraticStrategy implements SearchStrategy {
    private int q = 1;

    public int searchStep(boolean updateStep) {
        if (updateStep) {
            q = 1;
        }
        return (int) Math.pow(q++, 2);
    }
}
