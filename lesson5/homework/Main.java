package lesson5.homework;

import lesson5.homework.factories.LinearSolver;
import lesson5.homework.factories.QuadraticSolver;

public class Main {
    public static void main(String[] args) {

        Hashmap hm = new Hashmap(20, new QuadraticSolver());
//        Hashmap hm = new Hashmap(20, new LinearSolver(1));

        hm.put("1", new Point(1,1));
        hm.put("2", new Point(1,0));
        hm.put("3", new Point(0,0));
        hm.put("4", new Point(0,1));

        System.out.println(hm.get("4"));
        System.out.println(hm.getOrElse("5", new Point(5,5)));
        System.out.println(hm.contains("5"));
        System.out.println(hm.remove("3"));
        System.out.println(hm.getSafe("3"));

    }
}
