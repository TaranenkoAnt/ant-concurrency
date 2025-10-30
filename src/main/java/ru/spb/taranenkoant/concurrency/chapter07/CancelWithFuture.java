package ru.spb.taranenkoant.concurrency.chapter07;


import java.util.concurrent.*;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 30.10.2025
 */
public class CancelWithFuture<T> {

    private final ExecutorService taskExec = Executors.newFixedThreadPool(100);

    public void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);

        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {

        } catch (ExecutionException e) {
            throw  launderThrowable(e.getCause());
        } finally {
            // Безвредно, если задача уже завершена
            task.cancel(true);
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
