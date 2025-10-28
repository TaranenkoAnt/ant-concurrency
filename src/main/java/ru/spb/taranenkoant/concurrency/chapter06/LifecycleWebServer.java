package ru.spb.taranenkoant.concurrency.chapter06;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 28.10.2025
 */
public class LifecycleWebServer {

    private final ExecutorService exec = Executors.newFixedThreadPool(100);

    public void start() throws IOException {
        try (ServerSocket socket = new ServerSocket(80)) {
            while (!exec.isShutdown()) {
                try {
                    final Socket conn = socket.accept();
                    exec.execute(() -> {
                        handleRequest(conn);
                    });
                } catch (RejectedExecutionException e) {
                    if (!exec.isShutdown()) {
                        System.out.println("Задача отклонена - " + e.getMessage());
                    }
                }
            }
        }
    }

    public void stop() {
        exec.shutdown();
    }

    private void handleRequest(Socket conn) {
        Request  req = readRequest(conn);
        if (isShutdownRequest(req)) {
            stop();
        } else {
            dispatchRequest(req);
        }
    }

    private boolean isShutdownRequest(Request req) {
        return false;
    }

    private void dispatchRequest(Request req) {

    }

    private Request readRequest(Socket conn) {
        return new Request();
    }

    private static class Request {
    }
}
