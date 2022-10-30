package lesson5.homework.src.HashTable;

import lesson5.homework.src.Interfaces.OrdinaryInterface;

import java.util.Optional;

public class HashTable implements OrdinaryInterface {
    private final OrdinaryHashMap hashMap;

    private HashTable(OrdinaryBuilder ordinaryBuilder) {
        this.hashMap = ordinaryBuilder.hashMap;
    }

    @Override
    public Point get(String k) {
        return hashMap.get(k);
    }

    @Override
    public Optional<Point> getSafe(String k) {
        return hashMap.getSafe(k);
    }

    @Override
    public Point getOrElse(String k, Point val) {
        return hashMap.getOrElse(k, val);
    }

    @Override
    public boolean contains(String k) {
        return hashMap.contains(k);
    }

    @Override
    public Optional<Point> remove(String k) {
        return hashMap.remove(k);
    }

    @Override
    public void put(String k, Point val) {
        hashMap.put(k, val);
    }

    public void clear() {
        hashMap.clear();
    }

    public static class LinearBuilder extends OrdinaryBuilder {

        private LinearBuilder(SearchTechniques searchTechnique) throws IllegalArgumentException {
            if (searchTechnique.equals(SearchTechniques.LINEAR)) {
                hashMap = new LinearHashMap();
            } else throw new IllegalArgumentException();
        }

        public static LinearBuilder newInstance(SearchTechniques searchTechnique) {
            return new LinearBuilder(searchTechnique);
        }

        public LinearBuilder setStep(int step) {
            hashMap.setStep(step);
            return this;
        }
    }

    public static class OrdinaryBuilder {
        OrdinaryHashMap hashMap;

        private OrdinaryBuilder() {
            hashMap = new OrdinaryHashMap();
        }

        private OrdinaryBuilder(SearchTechniques searchTechnique) throws IllegalArgumentException {
            if (searchTechnique.equals(SearchTechniques.QUADRATIC)) {
                hashMap = new QuadraticHashMap();
            } else if (searchTechnique.equals(SearchTechniques.ENUMERATE)) {
                hashMap = new OrdinaryHashMap();
            } else throw new IllegalArgumentException();
        }

        public static OrdinaryBuilder newInstance(SearchTechniques searchTechnique) {
            return new OrdinaryBuilder(searchTechnique);
        }

        public HashTable build() {
            return new HashTable(this);
        }
    }
}
