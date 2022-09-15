package com.hsai.auxclasses;

public class AlgDeterm {
    public static int Determine(int inp) {
        if (inp >= 0) {
            return inp % algCnt;
        }
        else {
            return algCnt - (-1) * inp % 4;
        }
    };
}