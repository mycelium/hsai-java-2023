package lesson5.homework.src;

import lesson5.homework.src.HashMap.HashMap;
import lesson5.homework.src.HashMap.Point;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("Tests for linear strategy with step = 1");
        HashMap map = HashMap.linearBuilder().setCapacity(10).setStep(1).build();
        map.put("first", new Point(0, 0));
        map.put("second", new Point(1, 1));
        map.put("third", new Point(-2, -2));

        if (map.contains("second")) {
            System.out.println("PUT and CONTAINS methods are correct.");
        }

        Point somePoint = map.get("third");
        if (somePoint.x() == -2 && somePoint.y() == -2) {
            System.out.println("GET method are correct.");
        }

        Point somePoint_ = map.getOrElse("fifth", new Point(4, 0));
        if (somePoint_.equals(new Point(4, 0))) {
            System.out.println("GETORELSE method are correct.");
        }

        Optional<Point> var = map.getSafe("sixth");
        if (var.isEmpty()) {
            System.out.println("GETSAFE method are correct.");
        }

        map.remove("first");
        if (!map.contains("first")) {
            System.out.println("REMOVE method are correct.");
        }

        System.out.println("Tests for linear strategy with custom step (in this case it's 5)");
        HashMap map1 = HashMap.linearBuilder().setCapacity(10).setStep(5).build();
        map1.put("first", new Point(0, 0));
        map1.put("second", new Point(1, 1));
        map1.put("third", new Point(-2, -2));

        if (map1.contains("second")) {
            System.out.println("PUT and CONTAINS methods are correct.");
        }

        Point somePoint1 = map1.get("third");
        if (somePoint1.x() == -2 && somePoint1.y() == -2) {
            System.out.println("GET method are correct.");
        }

        Point somePoint1_ = map1.getOrElse("fifth", new Point(4, 0));
        if (somePoint1_.equals(new Point(4, 0))) {
            System.out.println("GETORELSE method are correct.");
        }

        Optional<Point> var1 = map1.getSafe("sixth");
        if (var1.isEmpty()) {
            System.out.println("GETSAFE method are correct.");
        }

        map1.remove("first");
        if (!map1.contains("first")) {
            System.out.println("REMOVE method are correct.");
        }

        System.out.println("Tests for quadratic strategy");
        HashMap mapQ = HashMap.quadraticBuilder().setCapacity(10).build();
        mapQ.put("first", new Point(0, 0));
        mapQ.put("second", new Point(1, 1));
        mapQ.put("third", new Point(-2, -2));

        if (mapQ.contains("second")) {
            System.out.println("PUT and CONTAINS methods are correct.");
        }

        Point somePointQ = mapQ.get("third");
        if (somePointQ.x() == -2 && somePointQ.y() == -2) {
            System.out.println("GET method are correct.");
        }

        Point somePointQ_ = mapQ.getOrElse("fifth", new Point(4, 0));
        if (somePointQ_.equals(new Point(4, 0))) {
            System.out.println("GETORELSE method are correct.");
        }

        Optional<Point> varQ = mapQ.getSafe("sixth");
        if (varQ.isEmpty()) {
            System.out.println("GETSAFE method are correct.");
        }

        mapQ.remove("first");
        if (!mapQ.contains("first")) {
            System.out.println("REMOVE method are correct.");
        }
    }
}
