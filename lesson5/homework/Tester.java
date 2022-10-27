package lesson5.homework;

import java.util.Optional;
import lesson5.homework.src.HashMap.*;

public class Tester {
    public static void main(String[] args) {
        MyHashMap map = MyHashMap.linearBuilder().setStep(3).build();

        map.put("pointX", new Point(1, 0));
        map.put("pointX", new Point(3, 0));
        if (map.getSize() == 1) System.out.println("Same key test successful.");
        else System.out.println("Same key test unsuccessful.");

        map.put("pointY", new Point(0, 1));
        map.put("pointXY", new Point(1, 1));
        if (map.contains("pointXY") && !map.contains("point-XY")) System.out.println("put and contains methods work.");
        else System.out.println("put and contains methods don't work.");

        if (map.get("pointX").equals(new Point(1, 0)) &&
            !map.get("pointXY").equals(new Point(0, 1))) System.out.print("get method works.\n");
        else System.out.print("get method doesn't work.\n");

        if (map.getOrElse("point-XY", new Point(-1, -1)).equals(new Point(-1, -1))) System.out.print("getOrElse method works.\n");
        else System.out.print("getOrElse method doesn't work.\n");

        if (map.getSafe("point0").isEmpty()) System.out.print("getSafe method works.\n");
        else System.out.print("getSafe method doesn't work.\n");

        map.remove("pointY");
        if (!map.contains("pointY")) System.out.print("remove method works.\n");
        else System.out.print("remove method doesn't work.\n");

        map.put("point-XY", new Point(-1, -1));
        map.put("point2XY", new Point(2, 2));
        if (map.getCapacity() == 8) System.out.println("Extend table test successful.");
        else System.out.println("Extend table test unsuccessful.");
    }
}