package lesson5.homework;

import lesson5.homework.HashMap.*;
import lesson5.homework.SearchStrategies.*;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        System.out.println("Testing incorrect constructors:");
        CKHashMap errmap;
        try {
            errmap = new CKHashMap(StrategyNames.UNUSED);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            errmap = new CKHashMap(StrategyNames.LINEAR, -2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        Optional<Point> point;
        System.out.println();
        System.out.println("Testing linear strategy:");
        CKHashMap map = new CKHashMap(StrategyNames.LINEAR, 3);

        map.put("Hello", new Point(3, 4));
        map.put("World", new Point(6, 9));
        map.put("Plant", new Point(3, 3));
        map.put("House", new Point(-1, -2));
        map.put("Letter", new Point(3, 0));
        map.put("Letter", new Point(0, 3));
        map.put("Sunset", new Point(11, -2));
        map.PrintMap();
        System.out.println(map.contains("Letter"));
        System.out.println(map.contains("Fence"));
        System.out.println(map.contains("Hello"));

        point = map.getSafe("Letter");
        System.out.println("Value of Key Letter:" + point.get().x() + " " + point.get().y());

        map.remove("Pigeon");
        map.remove("Letter");
        map.remove("House");
        map.PrintMap();
        System.out.println("Map contains key Letter? " + map.contains("Letter"));

        System.out.println();
        System.out.println("Testing sequential strategy:");
        map = new CKHashMap(StrategyNames.SEQUENTIAL);

        map.put("Hello", new Point(3, 4));
        map.put("World", new Point(6, 9));
        map.put("Plant", new Point(3, 3));
        map.put("House", new Point(-1, -2));
        map.put("Letter", new Point(3, 0));
        map.put("Letter", new Point(0, 3));
        map.put("Sunset", new Point(11, -2));
        map.PrintMap();
        System.out.println(map.contains("Letter"));
        System.out.println(map.contains("Fence"));
        System.out.println(map.contains("Hello"));

        point = map.getSafe("Letter");
        System.out.println("Value of Key Letter:" + point.get().x() + " " + point.get().y());

        map.remove("Pigeon");
        map.remove("Letter");
        map.remove("House");
        map.PrintMap();
        System.out.println("Map contains key Letter? " + map.contains("Letter"));

        System.out.println();
        System.out.println("Testing quadratic strategy:");
        map = new CKHashMap(StrategyNames.QUADRATIC);

        map.put("Hello", new Point(3, 4));
        map.put("World", new Point(6, 9));
        map.put("Plant", new Point(3, 3));
        map.put("House", new Point(-1, -2));
        map.put("Letter", new Point(3, 0));
        map.put("Letter", new Point(0, 3));
        map.put("Sunset", new Point(11, -2));
        map.PrintMap();
        System.out.println(map.contains("Letter"));
        System.out.println(map.contains("Fence"));
        System.out.println(map.contains("Hello"));

        point = map.getSafe("Letter");
        System.out.println("Value of Key Letter:" + point.get().x() + " " + point.get().y());

        map.remove("Pigeon");
        map.remove("Letter");
        map.remove("House");
        map.PrintMap();
        System.out.println("Map contains key Letter? " + map.contains("Letter"));
    }
}
