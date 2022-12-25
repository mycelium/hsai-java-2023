package lesson8.homework;

import lesson8.homework.converter.*;
import lesson8.homework.exceptions.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Converter C = new Converter(args[0], args[1], args[2], args[3]);
            C.convert();
        } catch (InvalidFormat e) {
            System.out.println(e.getMessage());
        }
    }
}
