package com.hsai;

import com.hsai.myhashtab.*;
import com.hsai.myhashtab.exceptions.*;
import com.hsai.myhashtab.strategies.*;
import com.hsai.mypoint.*;

public class Main {
    public static void main(String[] args) {
        MyPoint p1 = new MyPoint(1,1);
        MyPoint p2 = new MyPoint(2,2);
        try {
            System.out.println(" ");
            System.out.println("----Sequent hash table----");
            MyHashTab ht1 = new MyHashTab(0, Strategy.SEQUENT);
            ht1.put("A", p1);
            ht1.put("Q", p1);  // A and Q collide     indexA = 1 & indexQ = 1
            ht1.put("g", p2);
            ht1.put("U", p1);
            ht1.put("S", p2);
            ht1.put("S", p1);
            ht1.put("M", p2);
            ht1.remove("Q");
            System.out.println(ht1.get("A"));
            MyPoint F = new MyPoint(-1,-1);
            System.out.println(ht1.getOrElse("amogus", F));
            System.out.println(ht1.getSafe("Pudge"));
            ht1.PrintHashTab();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                System.out.println(" ");
                System.out.println("----Linear hash table----");
                MyHashTab ht2 = new MyHashTab(10000, Strategy.LIN);
                //ht2.put("A", p1);
                //ht2.put("Q", p1);  
                //ht2.put("g", p2);
                //ht2.put("U", p1);
                //ht2.put("S", p2);
                //ht2.put("S", p1);
                //ht2.put("M", p2);
                //ht2.remove("Q");
                //ht2.PrintHashTab();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    System.out.println(" ");
                    System.out.println("----Quadr hash table----");
                    MyHashTab ht3 = new MyHashTab(0, Strategy.QUADR);
                    ht3.put("A", p1);
                    ht3.put("Q", p2);
                    ht3.put("a", p2);
                    ht3.put("W", p2);
                    ht3.put("g", p2);
                    ht3.PrintHashTab();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } 
            }
        }
    }
}
