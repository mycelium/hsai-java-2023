package com.hsai.hashmap;

import java.util.Optional;

import com.hsai.point.*;
import com.hsai.hashmap.exceptions.*;

public interface HashMapInterface {
    public void put(String key, Point value);

    public Point get(String key);

    public Point getOrElse(String key, Point defaultValue);

    public Optional<Point> getSafe(String key);

    public boolean contains(String key);

    public Optional<Point> remove(String key);
}
