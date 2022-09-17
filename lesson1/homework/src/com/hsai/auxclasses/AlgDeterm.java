package com.hsai.auxclasses;

public class AlgDeterm {
    public static final int ALG_CNT = 4;
    
    public static int Determine(int inp) {
        if (inp >= 0) {
            return inp % ALG_CNT;
        }
        else {
            return inp + ALG_CNT * -1 * inp / 4;
        }
    };
}