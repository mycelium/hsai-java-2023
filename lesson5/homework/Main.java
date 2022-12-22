package lesson5.homework;

import lesson5.homework.Hashmap.Hashmap;
import lesson5.homework.search.LinearSearch;
import lesson5.homework.search.QuadraticSearch;

import java.awt.*;
import java.awt.Point;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("TESTING MAP WITH SEQUENTIAL SEARCHING");
        Hashmap map = new Hashmap(20, new LinearSearch(1));
        map.put("Center", new Point(10, 10));
        map.put("Left", new Point(-2, 5));
        map.put("Center", new Point(1, 1));
        map.put("Right", new Point(18, 0));
        System.out.println("Hashmap generated.");

        System.out.println("Testing method get(key) for Left key:");
        Point centerPoint = map.get("Left");
        if (centerPoint.x == -2 && centerPoint.y == 5) {
            System.out.println("map get method run successful");
        }

        System.out.println("Testing method getOrElse(key) for Button key(not present in current hashmap):");
        centerPoint = map.getOrElse("Button", new Point(0, 0));
        if (centerPoint.x == 0 && centerPoint.y == 0) {
            System.out.println("map getOrElse method run successful");
        }

        System.out.println("Testing method getSafe(key) for Up key(not present in current hashmap):");
        Optional<Point> var = map.getSafe("Up");
        if (var.isEmpty()) {
            System.out.println("map getSafe method run successful");
        }

        System.out.println("Testing method contains(key) for Center key:");
        if (map.contains("Center")) {
            System.out.println("map contains method run successful");
        }

        System.out.println("Testing method remove(key) for Center key");
        map.remove("Center");
        if (!map.contains("Center")) {
            System.out.println("map remove method run successful");
        }

        System.out.println("TESTING MAP WITH LINEAR SEARCHING");
        Hashmap map2 = new Hashmap(20, new LinearSearch(1));
        map2.put("Center", new Point(10, 10));
        map2.put("Left", new Point(-2, 5));
        map2.put("Center", new Point(1, 1));
        map2.put("Right", new Point(18, 0));
        System.out.println("Hashmap generated.");

        System.out.println("Testing method get(key) for Left key:");
        Point centerPoint2 = map2.get("Left");
        if (centerPoint2.x == -2 && centerPoint2.y == 5) {
            System.out.println("map get method run successful");
        }

        System.out.println("Testing method getOrElse(key) for Button key(not present in current hashmap):");
        centerPoint2 = map2.getOrElse("Button", new Point(0, 0));
        if (centerPoint2.x == 0 && centerPoint2.y == 0) {
            System.out.println("map getOrElse method run successful");
        }

        System.out.println("Testing method getSafe(key) for Up key(not present in current hashmap):");
        Optional<Point> var2 = map2.getSafe("Up");
        if (var2.isEmpty()) {
            System.out.println("map getSafe method run successful");
        }

        System.out.println("Testing method contains(key) for Center key:");
        if (map2.contains("Center")) {
            System.out.println("map contains method run successful");
        }

        System.out.println("Testing method remove(key) for Center key");
        map2.remove("Center");
        if (!map2.contains("Center")) {
            System.out.println("map remove method run successful");
        }

        System.out.println("TESTING MAP WITH QUADRATIC SEARCHING");
        Hashmap map3 = new Hashmap(20, new QuadraticSearch());
        map3.put("Center", new Point(10, 10));
        map3.put("Left", new Point(-2, 5));
        map3.put("Center", new Point(1, 1));
        map3.put("Right", new Point(18, 0));
        System.out.println("Hashmap generated.");

        System.out.println("Testing method get(key) for Left key:");
        Point centerPoint3 = map3.get("Left");
        if (centerPoint3.x == -2 && centerPoint3.y == 5) {
            System.out.println("map get method run successful");
        }

        System.out.println("Testing method getOrElse(key) for Button key(not present in current hashmap):");
        centerPoint3 = map3.getOrElse("Button", new Point(0, 0));
        if (centerPoint3.x == 0 && centerPoint3.y == 0) {
            System.out.println("map getOrElse method run successful");
        }

        System.out.println("Testing method getSafe(key) for Up key(not present in current hashmap):");
        Optional<Point> var3 = map3.getSafe("Up");
        if (var3.isEmpty()) {
            System.out.println("map getSafe method run successful");
        }

        System.out.println("Testing method contains(key) for Center key:");
        if (map3.contains("Center")) {
            System.out.println("map contains method run successful");
        }

        System.out.println("Testing method remove(key) for Center key");
        map3.remove("Center");
        if (!map3.contains("Center")) {
            System.out.println("map remove method run successful");
        }
    }
}
