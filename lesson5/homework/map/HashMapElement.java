package lesson5.homework.map;

public record HashMapElement(String key, Point value, int hash) {

    public String getKey() {
        return this.key;
    }

    public Point getValue() {
        return this.value;
    }
}
