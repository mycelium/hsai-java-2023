package decoder;

public class TestClass {
    public static void test1() {
        if (!"something".equals(Decoder.decode("c29tZXRoaW5n"))) {
            throw new AssertionError("Метод работает неверно!");
        }
    }

    public static void test2() {
        if (!"helloworld".equals(Decoder.decode("aGVsbG93b3JsZA=="))) {
            throw new AssertionError("Метод работает неверно!");
        }
    }

    public static void test3() {
        if (!"русскиевперед".equals(Decoder.decode("0YDRg9GB0YHQutC40LXQstC/0LXRgNC10LQ="))) {
            throw new AssertionError("Метод работает неверно!");
        }
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
}
