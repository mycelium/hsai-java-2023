package com.hsai.auxclasses;

public class AlgDeterm {
    public static final int ALG_CNT = 4;
    
    public static int Determine(int inp) {
        inp %= ALG_CNT;
        if (inp >= 0) {
            return inp;
        }
        else {
            return (ALG_CNT + inp);
        }
    };
}