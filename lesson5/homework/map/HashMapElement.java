package lesson5.homework.map;

public class HashMapElement {
    String key;
    Point value;
    final int hash;

    public HashMapElement(String key, Point value) {
        this.key = key;
        this.value = value;
        hash = Math.abs(key.hashCode());
    }

    public int getHash() {
        return hash;
    }

    public String getKey() {
        return this.key;
    }

    public Point getValue() {
        return this.value;
    }
}
