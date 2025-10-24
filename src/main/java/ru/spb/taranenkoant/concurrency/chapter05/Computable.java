package ru.spb.taranenkoant.concurrency.chapter05;


/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 24.10.2025
 */
public interface Computable<A, V> {
    V compute(final A arg) throws InterruptedException;
}
