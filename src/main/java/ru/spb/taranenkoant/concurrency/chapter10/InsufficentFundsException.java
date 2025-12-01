package ru.spb.taranenkoant.concurrency.chapter10;


/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 01.12.2025
 */
public class InsufficentFundsException extends RuntimeException {

    public InsufficentFundsException() {
    }

    public InsufficentFundsException(String message) {
        super(message);
    }

    public InsufficentFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
