import utils.*;
import utils.hashUtils.MyPoint;
import utils.search.*;

public class Main {
    public static void sequentialStrategyTest() {
        System.out.println("Testing hashmap with Sequential Strategy");
        MyHashMap hashmap = new MyHashMap(5, new LinearSearch(1));
        hashmap.put("dorw", new MyPoint(0,1));
        hashmap.put("word", new MyPoint(0,1));
        hashmap.put("word", new MyPoint(1,2));
        hashmap.put("word", new MyPoint(2,3));
        hashmap.put("word", new MyPoint(4,5));
        hashmap.put("word", new MyPoint(5,6));
        hashmap.put("ergergr", new MyPoint(0,1));
        System.out.println("Hashmap generated:");
        hashmap.printTable();
        System.out.println("Deleting 4 elements with key word");
        System.out.println(hashmap.remove("word"));
        System.out.println(hashmap.remove("word"));
        System.out.println(hashmap.remove("word"));
        System.out.println(hashmap.remove("word"));
        System.out.println("After deletion:");
        hashmap.printTable();
        System.out.println("Testing method get(key) for gibbidy key(not present in current hashmap):");
        System.out.println(hashmap.get("gibbidy"));
        System.out.println("Testing method get(key) for dorw key:");
        System.out.println(hashmap.get("dorw"));
        System.out.println("Testing method getOrElse(key) for gibbidy key(not present in current hashmap):");
        System.out.println(hashmap.getOrElse("gibbidy", new MyPoint(13,37)));
        System.out.println("Testing method getOrElse(key) for ergergr key:");
        System.out.println(hashmap.getOrElse("ergergr", new MyPoint(13,37)));
        System.out.println("Testing method contains(key) for gibbidy key(not present in current hashmap):");
        System.out.println(hashmap.contains("gibbidy"));
        System.out.println("Testing method contains(key) for dorw key:");
        System.out.println(hashmap.contains("dorw"));
        System.out.println("Testing method getSafe(key) for gibbidy key(not present in current hashmap):");
        System.out.println(hashmap.getSafe("gibbidy"));
        System.out.println("Testing method getSafe(key) for dorw key:");
        System.out.println(hashmap.getSafe("dorw"));
    }

    public static void linearStrategyTest() {
        System.out.println("Testing hashmap with Linear Strategy");
        MyHashMap hashmap = new MyHashMap(5, new LinearSearch(4));
        hashmap.put("dorw", new MyPoint(0,1));
        hashmap.put("word", new MyPoint(0,1));
        hashmap.put("word", new MyPoint(1,2));
        hashmap.put("word", new MyPoint(2,3));
        hashmap.put("word", new MyPoint(4,5));
        hashmap.put("word", new MyPoint(5,6));
        hashmap.put("ergergr", new MyPoint(0,1));
        System.out.println("Hashmap generated:");
        hashmap.printTable();
        System.out.println("Deleting 4 elements with key word");
        System.out.println(hashmap.remove("word"));
        System.out.println(hashmap.remove("word"));
        System.out.println(hashmap.remove("word"));
        System.out.println(hashmap.remove("word"));
        System.out.println("After deletion:");
        hashmap.printTable();
        System.out.println("Testing method get(key) for gibbidy key(not present in current hashmap):");
        System.out.println(hashmap.get("gibbidy"));
        System.out.println("Testing method get(key) for dorw key:");
        System.out.println(hashmap.get("dorw"));
        System.out.println("Testing method getOrElse(key) for gibbidy key(not present in current hashmap):");
        System.out.println(hashmap.getOrElse("gibbidy", new MyPoint(13,37)));
        System.out.println("Testing method getOrElse(key) for ergergr key:");
        System.out.println(hashmap.getOrElse("ergergr", new MyPoint(13,37)));
        System.out.println("Testing method contains(key) for gibbidy key(not present in current hashmap):");
        System.out.println(hashmap.contains("gibbidy"));
        System.out.println("Testing method contains(key) for dorw key:");
        System.out.println(hashmap.contains("dorw"));
        System.out.println("Testing method getSafe(key) for gibbidy key(not present in current hashmap):");
        System.out.println(hashmap.getSafe("gibbidy"));
        System.out.println("Testing method getSafe(key) for dorw key:");
        System.out.println(hashmap.getSafe("dorw"));
    }
    public static void quadraticStrategyTest() {
        System.out.println("Testing hashmap with Quadratic Strategy");
        MyHashMap hashmap = new MyHashMap(5, new QuadraticSearch());
        hashmap.put("dorw", new MyPoint(0,1));
        hashmap.put("word", new MyPoint(0,1));
        hashmap.put("word", new MyPoint(1,2));
        hashmap.put("word", new MyPoint(2,3));
        hashmap.put("word", new MyPoint(4,5));
        hashmap.put("word", new MyPoint(5,6));
        hashmap.put("ergergr", new MyPoint(0,1));
        System.out.println("Hashmap generated:");
        hashmap.printTable();
        System.out.println("Deleting 4 elements with key word");
        System.out.println(hashmap.remove("word"));
        System.out.println(hashmap.remove("word"));
        System.out.println(hashmap.remove("word"));
        System.out.println(hashmap.remove("word"));
        System.out.println("After deletion:");
        hashmap.printTable();
        System.out.println("Testing method get(key) for gibbidy key(not present in current hashmap):");
        System.out.println(hashmap.get("gibbidy"));
        System.out.println("Testing method get(key) for dorw key:");
        System.out.println(hashmap.get("dorw"));
        System.out.println("Testing method getOrElse(key) for gibbidy key(not present in current hashmap):");
        System.out.println(hashmap.getOrElse("gibbidy", new MyPoint(13,37)));
        System.out.println("Testing method getOrElse(key) for ergergr key:");
        System.out.println(hashmap.getOrElse("ergergr", new MyPoint(13,37)));
        System.out.println("Testing method contains(key) for gibbidy key(not present in current hashmap):");
        System.out.println(hashmap.contains("gibbidy"));
        System.out.println("Testing method contains(key) for dorw key:");
        System.out.println(hashmap.contains("dorw"));
        System.out.println("Testing method getSafe(key) for gibbidy key(not present in current hashmap):");
        System.out.println(hashmap.getSafe("gibbidy"));
        System.out.println("Testing method getSafe(key) for dorw key:");
        System.out.println(hashmap.getSafe("dorw"));
    }
    public static void main(String[] args) {
        sequentialStrategyTest();
        linearStrategyTest();
        quadraticStrategyTest();
    }


}