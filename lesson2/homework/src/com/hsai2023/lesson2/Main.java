package com.hsai2023.lesson2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("started");
        int counter = 0;
        while(true) {
            Thread.sleep(2_000);
            counter++;
        }
    }
}
