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

        HashTable quadratic = new HashTable(SearchTechniques.QUADRATIC);
        //quadratic.setStep(17);
        quadratic.put("one", new Point(1, 1));
        Point returnPoint = quadratic.get("one");
        quadratic.remove("one");

        HashTable linear = new HashTable(SearchTechniques.LINEAR);
        linear.setStep(87);
        //linear.setStep(14);
        linear.put("one", new Point(1, 1));
        returnPoint = linear.get("one");
        linear.remove("one");

        HashTable enumerating = new HashTable(SearchTechniques.ENUMERATE);
        //enumerating.setStep(76);
        enumerating.put("one", new Point(1, 1));
        returnPoint = enumerating.get("one");
        enumerating.remove("one");
    }
}
