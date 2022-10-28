package lesson5.homework.src.SearchStrategy;

import lesson5.homework.src.HashMap.HashMap_Element;

public class QuadraticSearch extends SearchStrategy_Abstract {
    static int q = 1;

    public static void reset() {
        q = 1;
    }

    protected int newHash(int hash) {
        hash += q * q;
        q++;
        return hash;
    }
}
