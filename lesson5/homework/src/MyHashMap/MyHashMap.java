package lesson5.homework.src.MyHashMap;

import lesson5.homework.src.Interfaces.LinearInterface;
import lesson5.homework.src.Interfaces.OrdinaryInterface;

import java.util.Optional;

public class MyHashMap implements LinearInterface {
    OrdinaryInterface table;

    public MyHashMap(SearchTechniques searchTechnique) {
        if (searchTechnique.equals(SearchTechniques.LINEAR)) {
            table = new LinearHashMap(searchTechnique);
        } else if (searchTechnique.equals(SearchTechniques.QUADRATIC)) {
            table = new QuadraticHashMap(searchTechnique);
        } else {
            table = new OrdinaryHashMap(searchTechnique);
        }

    }

    public void setStep(int s) {
        ((LinearInterface) table).setStep(s);
    }

    @Override
    public Point get(String k) {
        return table.get(k);
    }

    @Override
    public Optional<Point> getSafe(String k) {
        return table.getSafe(k);
    }

    @Override
    public Point getOrElse(String k, Point val) {
        return table.getOrElse(k, val);
    }

    @Override
    public boolean contains(String k) {
        return table.contains(k);
    }

    @Override
    public Optional<Point> remove(String k) {
        return table.remove(k);
    }

    @Override
    public void put(String k, Point val) {
        table.put(k, val);
    }
}
