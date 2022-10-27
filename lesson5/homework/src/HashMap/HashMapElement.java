package lesson5.homework.src.HashMap;

public record HashMapElement(String key, Point value, int hash) {
    public static HashMapElement nullElement = new HashMapElement("", null);
    public HashMapElement(String key, Point value) {
        this(key, value, Math.abs(key.hashCode()));
    }

    public int getHash() {
        return this.hash;
    }

    public String getKey() {
        return this.key;
    }

    public Point getValue() {
        return this.value;
    }
}

