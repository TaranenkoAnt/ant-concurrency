package ru.spb.taranenkoant.concurrency.chapter07;


import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 30.10.2025
 */
public class ReaderThread extends Thread {

    private static final int BUFZ = 1000;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {

        } finally {
            super.interrupt();
        }
    }

    public void run() {
        try {
            byte[] buf = new byte[BUFZ];
            while (true) {
                int count = in.read(buf);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    processBuffer(buf, count);
                }
            }
        } catch (IOException e) {

        }
    }

    private void processBuffer(byte[] buf, int count) {

    }
}
