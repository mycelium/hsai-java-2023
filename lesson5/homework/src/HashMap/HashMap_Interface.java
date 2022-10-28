package lesson5.homework.src.HashMap;

import java.util.Optional;

public interface HashMap_Interface {
    void put(String key, Point value);

    Point get(String key);

    Point getOrElse(String key, Point value);

    Optional<Point> getSafe(String key);

    boolean contains(String key);

    Optional<Point> remove(String key);
}
