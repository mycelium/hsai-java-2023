package lesson5.homework.Hashmap;

import java.awt.*;
import java.util.Optional;

public interface InterfaceHashmap {
    Point get(String var1);

    Optional<Point> getSafe(String var1);

    Point getOrElse(String var1, Point var2);

    boolean contains(String var1);

    Optional<Point> remove(String var1);

    void put(String var1, Point var2);
}