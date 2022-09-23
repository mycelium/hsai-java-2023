package com.hsai.additional;

public class SelectAlg {
    public static int ALG_COUNT = 4;

    public static int determineAlg(int algNum) {
        algNum %= ALG_COUNT;
        if (algNum >= 0) {
            return algNum;
        } 
        else {
            return (ALG_COUNT + algNum);
        }
    };
}