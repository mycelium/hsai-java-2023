package lesson5.homework.src;

import lesson5.homework.src.HashTable.HashTable;
import lesson5.homework.src.HashTable.HashTableTest;
import lesson5.homework.src.HashTable.Point;
import lesson5.homework.src.HashTable.SearchTechniques;

public class Main {
    public static void main(String[] args) {

        HashTableTest test = new HashTableTest(SearchTechniques.ENUMERATE);
        HashTableTest test1 = new HashTableTest(SearchTechniques.LINEAR);
        HashTableTest test2 = new HashTableTest(SearchTechniques.QUADRATIC);

        try {
            test.tests();
            test1.tests();
            test2.tests();
        } catch (Exception var8) {
            throw new RuntimeException(var8);
        }

        HashTable quadratic = HashTable.OrdinaryBuilder.newInstance(SearchTechniques.QUADRATIC).build();
        quadratic.put("one", new Point(1, 1));
        Point returnPoint = quadratic.get("one");
        quadratic.remove("one");
        System.out.println(returnPoint.toString());

        HashTable linear = HashTable.LinearBuilder.newInstance(SearchTechniques.LINEAR).setStep(31).build();
        linear.put("one", new Point(2, 2));
        returnPoint = linear.get("one");
        linear.remove("one");
        System.out.println(returnPoint.toString());

        HashTable enumerating = HashTable.OrdinaryBuilder.newInstance(SearchTechniques.ENUMERATE).build();
        enumerating.put("one", new Point(3, 3));
        returnPoint = enumerating.get("one");
        enumerating.remove("one");

        System.out.println(returnPoint.toString());
    }
}
