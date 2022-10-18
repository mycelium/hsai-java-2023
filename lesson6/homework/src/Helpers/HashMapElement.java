package lesson6.homework.src.Helpers;

public class HashMapElement {
    String key;
    Point value;

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

//    public static HashMapElement makeElement(String k, Point p ) {
//        return new HashMapElement(k,p);
//    }
}
