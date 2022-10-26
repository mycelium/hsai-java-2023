package lesson6.homework.src.Helpers;

public record HashMapElement(String key, Point value, int hash) {

    public static HashMapElement TOMBSTONE = new HashMapElement("", null);

    public HashMapElement(String key, Point value) {
        this(key, value, Math.abs(key.hashCode()));
    }

    public String getKey() {
        return this.key;
    }

    public Point getValue() {
        return this.value;
    }

    public int getHashCode() {
        return this.hash;
    }

}

