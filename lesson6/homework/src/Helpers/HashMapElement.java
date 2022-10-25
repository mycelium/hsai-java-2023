package lesson6.homework.src.Helpers;

public class HashMapElement {

    public static HashMapElement TOMBSTONE = new HashMapElement("", null);

    private String key;
    private Point value;

    int hashCode;

    public HashMapElement(String k, Point val) {
        this.key = k;
        this.value = val;
        this.hashCode = Math.abs(k.hashCode());
    }

    public String getKey() {
        return this.key;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }

    public Point getValue() {
        return this.value;
    }

    public int getHashCode() {
        return this.hashCode;
    }

}

