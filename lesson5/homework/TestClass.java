package lesson5.homework;

import lesson5.homework.map.*;

import lesson5.homework.strategy.*;

import java.util.Optional;

public class TestClass {
    public static void main(String[] args) {
        System.out.println("TESTING MAP WITH SERIAL SEARCHING");
        CustomHashMap map = new CustomHashMap(new SerialStrategy());
        map.put("Center", new Point(10, 10));
        map.put("Left", new Point(-2, 5));
        map.put("Center", new Point(1, 1));
        map.put("Right", new Point(18, 0));
        map.print();

        Point centerPoint = map.get("Center");
        if (centerPoint.x() == 10 && centerPoint.y() == 10) {
            System.out.println("map get method run successful");
        }

        centerPoint = map.getOrElse("Button", new Point(0, 0));
        if (centerPoint.x() == 0 && centerPoint.y() == 0) {
            System.out.println("map getOrElse method run successful");
        }

        Optional<Point> var = map.getSafe("Up");
        if (var.isEmpty()) {
            System.out.println("map getSafe method run successful");
        }

        if (map.contains("Center")) {
            System.out.println("map contains method run successful");
        }

        map.remove("Center");
        if (!map.contains("Center")) {
            System.out.println("map remove method run successful");
        }

        System.out.println("TESTING MAP WITH LINEAR SEARCHING");
        CustomHashMap map2 = new CustomHashMap(new LinearStrategy(2));
        map2.put("Center", new Point(10, 10));
        map2.put("Left", new Point(-2, 5));
        map2.put("Center", new Point(1, 1));
        map2.put("Right", new Point(18, 0));
        map2.print();

        Point centerPoint2 = map2.get("Center");
        if (centerPoint2.x() == 10 && centerPoint2.y() == 10) {
            System.out.println("map2 get method run successful");
        }

        centerPoint2 = map2.getOrElse("Button", new Point(0, 0));
        if (centerPoint2.x() == 0 && centerPoint2.y() == 0) {
            System.out.println("map2 getOrElse method run successful");
        }

        Optional<Point> var2 = map2.getSafe("Up");
        if (var2.isEmpty()) {
            System.out.println("map2 getSafe method run successful");
        }

        if (map2.contains("Center")) {
            System.out.println("map2 contains method run successful");
        }

        map2.remove("Center");
        if (!map2.contains("Center")) {
            System.out.println("map2 remove method run successful");
        }

        System.out.println("TESTING MAP WITH QUADRATIC SEARCHING");
        CustomHashMap map3 = new CustomHashMap(new QuadraticStrategy());
        map3.put("Center", new Point(10, 10));
        map3.put("Left", new Point(-2, 5));
        map3.put("Center", new Point(1, 1));
        map3.put("Right", new Point(18, 0));
        map3.print();

        Point centerPoint3 = map3.get("Center");
        if (centerPoint3.x() == 10 && centerPoint3.y() == 10) {
            System.out.println("map3 get method run successful");
        }

        centerPoint3 = map.getOrElse("Button", new Point(0, 0));
        if (centerPoint3.x() == 0 && centerPoint3.y() == 0) {
            System.out.println("map3 getOrElse method run successful");
        }

        Optional<Point> var3 = map3.getSafe("Up");
        if (var3.isEmpty()) {
            System.out.println("map3 getSafe method run successful");
        }

        if (map3.contains("Center")) {
            System.out.println("map3 contains method run successful");
        }

        map3.remove("Center");
        if (!map3.contains("Center")) {
            System.out.println("map3 remove method run successful");
        }
    }
}
