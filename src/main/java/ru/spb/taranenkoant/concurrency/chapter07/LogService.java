package ru.spb.taranenkoant.concurrency.chapter07;


import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 30.10.2025
 */
public class LogService {

    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final PrintWriter writer;

    private boolean isShutdown;
    private int reservation;

    public LogService(BlockingQueue<String> queue, LoggerThread loggerThread, PrintWriter writer) {
        this.queue = queue;
        this.loggerThread = loggerThread;
        this.writer = writer;
    }

    public void start() {
        loggerThread.start();
    }

    public void stop() throws InterruptedException {
        synchronized (this) {
            isShutdown = true;
            loggerThread.interrupt();
        }
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown) {
                throw new IllegalStateException();
            }
            ++reservation;
        }
        queue.put(msg);
    }

    private class LoggerThread extends Thread {

        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (this) {
                            if (isShutdown && reservation == 0) {
                                break;
                            }
                        }
                        String msg = queue.take();
                        synchronized (this) {
                            --reservation;
                        }
                        writer.println(msg);
                    } catch (InterruptedException e) {

                    }
                }
            } finally {
                writer.close();
            }
        }

        public void start() {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    LogService.this.stop();
                } catch (InterruptedException ignored) {

                }
            }));
        }

        public void interrupt() {
        }
    }
}



