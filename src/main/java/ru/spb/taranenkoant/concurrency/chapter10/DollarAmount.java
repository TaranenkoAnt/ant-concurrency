package ru.spb.taranenkoant.concurrency.chapter10;


/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 01.12.2025
 */
public class DollarAmount {

    private final int amount;
    public DollarAmount(int i) {
        this.amount = i;
    }

    public int getAmount() {
        return amount;
    }
}
