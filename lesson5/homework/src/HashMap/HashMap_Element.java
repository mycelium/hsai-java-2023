package lesson5.homework.src.HashMap;

public record HashMap_Element(String key, Point value, int hashNum) {

    public static HashMap_Element tombStone = new HashMap_Element("", null);

    public HashMap_Element(String key, Point value) {
        this(key, value, Math.abs(key.hashCode()));
    }

    public String getKey() {
        return this.key;
    }

    public Point getValue() {
        return this.value;
    }

    public int getHashCode() {
        return this.hashNum;
    }
}
