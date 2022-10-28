import utils.*;
import utils.hashUtils.MyPoint;
import utils.search.*;

public class Main {
    public static void main(String[] args) {

        MyHashMap hashmap = new MyHashMap(5, new LinearSearch(2));
        hashmap.put("dorw", new MyPoint(0,1));
        hashmap.put("word", new MyPoint(0,1));
        hashmap.put("word", new MyPoint(1,2));
        hashmap.put("word", new MyPoint(2,3));
        hashmap.put("word", new MyPoint(4,5));
        hashmap.put("word", new MyPoint(5,6));
        hashmap.put("ergergr", new MyPoint(0,1));
        hashmap.printTable();
        System.out.println(hashmap.remove("word"));
        System.out.println(hashmap.remove("word"));
        System.out.println(hashmap.remove("word"));
        System.out.println(hashmap.remove("word"));
        System.out.println("After deletion:");
        hashmap.printTable();
    }

    public void sequentialStrategyTest() {
        
    }
}