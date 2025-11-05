package ru.spb.taranenkoant.concurrency.chapter08;


import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 05.11.2025
 */
public class BoundedExecutor {

    private final Executor exec;
    private final Semaphore semaphore;

    public BoundedExecutor(Executor exec, Semaphore semaphore) {
        this.exec = exec;
        this.semaphore = semaphore;
    }

    public void submitTask(final Runnable command) throws InterruptedException {
        semaphore.acquire();

        try {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        command.run();
                    } finally {
                        semaphore.release();
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            semaphore.release();
        }
    }
}
