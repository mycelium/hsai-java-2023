package lesson5.homework.src.MyHashMap;

public class HashElement {
    String key;
    String stone;
    Point value;

    public HashElement(String k, Point val) {
        key = k;
        value = val;
        stone = "INSERTED";
    }

    public String getKey() {
        return key;
    }

    public Point getValue() {
        return value;
    }

    public void makeTombstone() {
        key = null;
        value = null;
        stone = "DELETED";
    }

    public boolean isTombstone() {
        return stone.equals("DELETED") && value == null && key == null;
    }
}
