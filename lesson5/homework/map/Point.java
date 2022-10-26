package lesson5.homework.map;

public record Point(int x, int y) {
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }
}