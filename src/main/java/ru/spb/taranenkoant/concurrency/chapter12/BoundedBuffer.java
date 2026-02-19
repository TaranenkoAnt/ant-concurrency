package ru.spb.taranenkoant.concurrency.chapter12;


import java.util.concurrent.Semaphore;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 16.02.2026
 */
public class BoundedBuffer<E> {

    private final Semaphore availableItems, availableSpaces;
    private final E[] items;
    private int putPosition = 0, takePositioon = 0;

    public BoundedBuffer(int capacity) {
        availableItems = new Semaphore(0);
        availableSpaces = new Semaphore(capacity);
        items = (E[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return availableItems.availablePermits() == 0;
    }

    public boolean isFull() {
        return availableSpaces.availablePermits() == 0;
    }

    public void put(E x) throws InterruptedException {
        availableSpaces.acquire();
        doInsert(x);
        availableItems.release();
    }

    private void doInsert(E x) {
        int i = putPosition;
        items[i] = x;
        putPosition = (++i == items.length) ? 0 : 1;
    }

    public E take() throws InterruptedException {
        availableItems.acquire();
        E item = doExtract();
        availableSpaces.release();
        return item;
    }

    private E doExtract() {
        int i = takePositioon;
        E x = items[i];
        takePositioon = (++i == items.length) ? 0 : 1;
        return x;
    }
}