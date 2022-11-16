package lesson6.homework;

import lesson6.homework.src.HashMap.*;
import lesson6.homework.src.Helpers.Point;

import java.util.Optional;


public class Main {
    public static void main(String[] args) {
        int defaultCapacity = 6;
        System.out.println("Total number of tests - 11");HashMap map = HashMap.linearBuilder().setStep(12).setCapacity(defaultCapacity).setLoadFactor(12).build();
        //HashMap map = HashMap.consistentBuilder().setCapacity(12).setStep(12).build();  //compilation error
        //
        map.put("Test", new Point(1, 1));
        if (map.contains("Test")) {
            System.out.println("1. Test \"Simple put and contains\": completed");
        }
        map.put("Test", new Point(2, 4));
        if (map.getSize() == 1) {
            System.out.println("2. Test \"Put with same keys\": completed");
        }
        if (!map.contains("Invisible Point")) {
            System.out.println("3. Test \"Find non-existent point\": completed");
        }
        map.put("1", new Point(1, 1));
        map.put("1<", new Point(1, 1));
        map.put("1<<", new Point(1, 1));
        map.put("1<<<", new Point(1, 1));
        if (map.getCapacity() > defaultCapacity) {
            System.out.println("4. Test \"Resize\": completed");
            if (map.contains("1<<")) {
                System.out.println("5. Test \"Put after resize\": completed");
            }
        }
        map.remove("1<");
        if (!map.contains("1<")) {
            System.out.println("6. Test \"Simple remove\": completed");
        }
        map.remove("1<<");
        map.put("1<", new Point(1, 1));
        if (!map.contains("1<<")) {
            System.out.println("7. Test \"Remove elements with same hash\": completed");
        }
        Optional<Point> empty = map.remove("non-existent Point");
        System.out.println("8. Test \"Return null after removing\": completed");
        map.put("null", null);
        System.out.println("9. Test \"Put null point\": completed");
        empty = map.getSafe("non-existent Point");
        empty = map.getSafe("null");
        System.out.println("10. Test \"Return null after getSafe\": completed");
        Point p = map.getOrElse("non-existent Point", new Point(12, 2));
        if (p.equals(new Point(12, 2))) {
            System.out.println("11. Test \"GetOrElse\": completed");
        }
    }
}