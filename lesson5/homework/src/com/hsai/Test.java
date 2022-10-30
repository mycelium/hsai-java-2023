package com.hsai;

import java.util.Optional;

import com.hsai.hashmap.*;
import com.hsai.point.Point;

public class Test {
    public static void runTest(HashMap map) {
        map.put("Blessings", new Point(0, 0));
        map.put("so", new Point(0, 0));
        map.put("sweet", new Point(23, 23));
        map.put("and", new Point(0, 0));
        map.put("divine", new Point(0, 0));
        map.put("None", new Point(0, 0));
        map.put("of", new Point(0, 0));
        map.put("which", new Point(0, 0));
        map.put("I", new Point(0, 0));
        map.put("do", new Point(0, 0));
        map.put("deserve", new Point(0, 0));

        map.put("Blessings", new Point(0, 0));
        map.put("Blessings", new Point(1, 1));

        boolean isPresent = map.contains("sweet");
        Point received = map.get("sweet");
        received = map.getOrElse("sweet", new Point(-1, -1));
        Optional<Point> option = map.getSafe("sweet");
        option = map.remove("sweet");

        isPresent = map.contains("something");
        received = map.get("something");
        received = map.getOrElse("something", new Point(-1, -1));
        option = map.getSafe("something");
        option = map.remove("something");

        System.out.println(map);
    }

    public static void main(String[] args) throws Exception {
        try {
            HashMap map = HashMap.builder().setSequentialStrategy().build();
            runTest(map);
        } catch (Exception e) {
            System.err.println("Sequential strategy failed! " + e);
        } finally {
            try {
                HashMap map = HashMap.builder().setLinearStrategy().setStep(3).build();
                runTest(map);
            } catch (Exception e) {
                System.err.println("Linear strategy failed! " + e);
            } finally {
                try {
                    HashMap map = HashMap.builder().setQuadraticStrategy().build();
                    runTest(map);
                } catch (Exception e) {
                    System.err.println("Quadratic strategy failed! " + e);
                }
            }
        }
    }
}
