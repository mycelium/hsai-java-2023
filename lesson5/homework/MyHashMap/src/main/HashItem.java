package main;

public record HashItem(String str, Point val) {
    public HashItem(String str, Point val) {
        this.str = str;
        this.val = val;
    }

    @Override
    public String str() {
        return str;
    }

    @Override
    public Point val() {
        return val;
    }

}
