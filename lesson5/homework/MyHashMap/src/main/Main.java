package main;
import java.util.*;
public class Main {
    public static void main(String[] argc){
        MyHashMap myMap = new MyHashMap();
        Package pack = myMap.getClass().getPackage();
        String str = pack.getName();
        Point pt = new Point(9,3);
        var map = new MyHashMap();
        map.setLookupStrategy("linear");
        map.setStep(22);
        map.put("1",new Point(1,1));
        var contains = map.contains("water");
        System.out.println(contains);
        map.remove("water");
        contains = map.contains("water");
        System.out.println(contains);
        return;
    }
}
