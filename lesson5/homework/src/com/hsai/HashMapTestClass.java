package com.hsai;

import java.util.ArrayList;

import com.hsai.myhashmap.*;
import com.hsai.myhashmap.interfaces.*;
import com.hsai.myhashmap.exceptions.*;
import com.hsai.mypoint.*;
import com.hsai.mypair.*;
import com.hsai.myfileio.*;
import java.util.Optional;

public class HashMapTestClass {
    static final String REGEX = "^([a-zA-Z]+)(?:\\W+)(\\d+)(?:\\W+)(\\d+)(?:\\W*)$";
    static final String INPUT_FILE = "test2.txt";

    public static void main(String[] args) throws Exception {
        var list = new ArrayList<MyPair<String, MyPoint>>();
        MyFileInput.readFile(INPUT_FILE/*args[0]*/, REGEX, list);

        try {
            MyHashMap hashMap = new MyHashMap(LookupStrategy.LINEAR);
            hashMap.setStep(17);
        } catch (IncorrectStepException e) {
            System.err.println(e + "\n\n");
        } finally {
            try {
                MyHashMap map = new MyHashMap(LookupStrategy.SEQUENTIAL);
                for (MyPair<String, MyPoint> myPair : list) {
                    map.put(myPair.key(), myPair.val());
                }

                System.out.println("Checking strategy setting: SEQUENIAL");
                runTest(map);

                System.out.println("Checking another strategy setting: LINEAR");
                map.setStrategy(LookupStrategy.LINEAR);
                map.setStep(4);

                runTest(map);

                System.out.println("Checking another strategy setting: QUADRATIC");
                map.setStrategy(LookupStrategy.QUADRATIC);
                runTest(map);

                System.out.println('\n');
            } catch (Exception e) {
                System.err.println(e + "\n\n");
            }
        }
    }

    static void runTest(MyHashMap map) {
        System.out.println(map.getSize());
        System.out.println(map.toString());
        System.out.println("\n\n");

        System.out.println("Inserting \"pen\" (duplicate)");
        map.put("pen", new MyPoint(2, 2));
        System.out.println(map.getSize());
        System.out.println(map.toString());
        System.out.println("\n\n");

        System.out.println("Inserting \"pen\" (with new value)");
        map.put("pen", new MyPoint(13, 29));
        System.out.println(map.getSize());
        System.out.println(map.toString());
        System.out.println("\n\n");

        System.out.println("Removing \"pen\"");
        map.remove("pen");
        System.out.println(map.getSize());
        System.out.println(map.toString());
        System.out.println("\n\n");

        System.out.println("Inserting \"pen\" again");
        map.put("pen", new MyPoint(2, 2));
        System.out.println(map.getSize());
        System.out.println(map.toString());
        System.out.println("\n\n");

        System.out.println("Removing \"something\"");
        map.remove("something");
        System.out.println(map.getSize());
        System.out.println(map.toString());
        System.out.println("\n\n");

        System.out.println("Checking if \"As\" is contained");
        System.out.println(map.contains("As"));
        System.out.println("Checking if \"something\" is contained");
        System.out.println(map.contains("something"));
        System.out.println('\n');

        System.out.println("Getting \"things\" with get()");
        System.out.println(map.get("things"));
        System.out.println("Getting \"things\" with getOrElse()");
        System.out.println(map.getOrElse("things", new MyPoint(-1, -1)));
        System.out.println("Getting \"things\" with getSafe()");
        System.out.println(map.getSafe("things"));
        System.out.println('\n');

        System.out.println("Getting \"something\" with get()");
        System.out.println(map.get("something"));
        System.out.println("Getting \"something\" with getOrElse()");
        System.out.println(map.getOrElse("something", new MyPoint(-1, -1)));
        System.out.println("Getting \"something\" with getSafe()");
        System.out.println(map.getSafe("something"));
        System.out.println('\n');
    }
}
