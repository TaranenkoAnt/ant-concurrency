package ru.spb.taranenkoant.concurrency.chapter4;


/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 23.10.2025
 */
public class MutablePoint {

    private long x;
    private long y;

    public MutablePoint(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public MutablePoint(MutablePoint loc) {
        this.x = loc.x;
        this.y = loc.y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }
}
