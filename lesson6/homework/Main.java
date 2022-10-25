package lesson6.homework;

import lesson6.homework.src.HashMap.*;
import lesson6.homework.src.Helpers.Point;

import java.util.Optional;


public class Main {
    public static void main(String[] args) {
        HashMap map = HashMap.linearBuilder().setCapacity(6).setStep(5).build();
        //HashMap map = HashMap.consistentBuilder().setCapacity(12).setStep(12).build();  //compilation error
        //
        map.contains("test");
        map.put("Test point", new Point(-2, 12));
        map.put("Point filler", null);
        map.remove("Point filler");
        map.put("", new Point(2, 3));
        map.put("Point filler", null);
        map.put("Lower left", new Point(-2, 12));
        map.put("Upper right", new Point(2, 2));
        Point leftPoint = map.get("Lower left");
        Point rightPoint = map.get("Upper right");
        if (leftPoint.x() < rightPoint.x()) {
            Point defaultCenter = new Point(2, 8);
            Point center = map.getOrElse("Center", defaultCenter);
            if (!map.contains("Center")) {
                map.put("Center", center);
            }
        }
        map.remove("lower left");
        if (map.contains("Lower left")) {
            map.remove("Lower left");
        }
        Optional<Point> p = map.getSafe("non-existent point");
        if (p.isPresent()) {
            int k = p.get().x();
        } else {
            map.put("non-existent point", new Point(12, 11));
        }
        p = map.getSafe("non-existent point");
        if (p.isPresent()) {
            int k = p.get().x();
        }
        p = map.remove("existent point");
        p = map.remove("non-existent point");
    }
}