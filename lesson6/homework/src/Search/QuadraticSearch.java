package lesson6.homework.src.Search;

import lesson6.homework.src.Helpers.HashMapElement;

public class QuadraticSearch extends AbstractSearch {
    static int q = 1;

    public static void reset() {
        q = 1;
    }

    protected int changedHash(int hash) {
        hash += q * q;
        q++;
        return hash;
    }

}
