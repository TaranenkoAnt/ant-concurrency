package ru.spb.taranenkoant.concurrency.chapter05;


import java.util.concurrent.ExecutionException;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 24.10.2025
 */
public class DataLoadException extends ExecutionException {

    public DataLoadException() {
    }

    public DataLoadException(String message) {
        super(message);
    }

    public DataLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataLoadException(Throwable cause) {
        super(cause);
    }
}
