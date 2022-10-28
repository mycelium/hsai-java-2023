package utils.hashUtils;

import java.util.Optional;
public interface MyHashInterface {
    void put(String key, MyPoint value);

    MyPoint get(String key);

    MyPoint getOrElse(String key, MyPoint value);

    Optional<MyPoint> getSafe(String key);

    boolean contains (String key);

    Optional<MyPoint> remove(String key);

}
