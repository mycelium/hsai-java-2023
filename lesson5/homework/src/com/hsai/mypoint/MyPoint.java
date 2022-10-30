package com.hsai.mypoint;

public record MyPoint(int x, int y) {
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
