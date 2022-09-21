package com.hsai.additional;

public class DetermAlg {
    public static int algCount = 4;

    public static int determine(int algNum) {
        algNum %= algCount;
        if (algNum >= 0) {
            return algNum;
        } 
        else {
            return (algCount + algNum);
        }
    };
}