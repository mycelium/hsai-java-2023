package com.hsai.auxclasses;

public class AlgDeterm {
    public static final int ALG_CNT = 4;

    public static int determine(int origAlgId) {
        origAlgId %= ALG_CNT;
        if (origAlgId >= 0) {
            return origAlgId;
        } else {
            return (ALG_CNT + origAlgId);
        }
    };
}