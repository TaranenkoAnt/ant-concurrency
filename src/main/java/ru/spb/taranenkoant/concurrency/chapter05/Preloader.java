package ru.spb.taranenkoant.concurrency.chapter05;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 24.10.2025
 */
public class Preloader {

    private final FutureTask<ProductInfo> future = new FutureTask<>(this::loadProductInfo);

    private ProductInfo loadProductInfo() {
        return new ProductInfo();
    }

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException) {
                throw (DataLoadException) cause;
            } else {
                throw launderThrowable(cause);
            }
        }
    }

    private RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Не является непроверяемым", t);
    }
}
