package lesson5.homework.src;

import lesson5.homework.src.MyHashMap.MyHashMap;
import lesson5.homework.src.MyHashMap.MyHashMapTest;
import lesson5.homework.src.MyHashMap.Point;
import lesson5.homework.src.MyHashMap.SearchTechniques;

public class Main {

    public static void main(String[] args) {
        MyHashMapTest test = new MyHashMapTest(SearchTechniques.ENUMERATE);
        MyHashMapTest test1 = new MyHashMapTest(SearchTechniques.LINEAR);
        MyHashMapTest test2 = new MyHashMapTest(SearchTechniques.QUADRATIC);

        try {
            test.Tests();
            test1.Tests();
            test2.Tests();
        } catch (Exception var8) {
            throw new RuntimeException(var8);
        }

        MyHashMap quadratic = new MyHashMap(SearchTechniques.QUADRATIC);
        //quadratic.setStep(17);
        quadratic.put("one", new Point(1, 1));
        Point returnPoint = quadratic.get("one");
        quadratic.remove("one");

        MyHashMap linear = new MyHashMap(SearchTechniques.LINEAR);
        linear.setStep(87);
        //linear.setStep(14);
        linear.put("one", new Point(1, 1));
        returnPoint = linear.get("one");
        linear.remove("one");

        MyHashMap enumerating = new MyHashMap(SearchTechniques.ENUMERATE);
        //enumerating.setStep(76);
        enumerating.put("one", new Point(1, 1));
        returnPoint = enumerating.get("one");
        enumerating.remove("one");
    }
}
