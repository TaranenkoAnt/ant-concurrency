package ru.spb.taranenkoant.concurrency.chapter05;


import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 24.10.2025
 */
public class Main {

    private static final int BOUND = 100; // Размер очереди
    private static final int N_CONSUMERS = 5; // Число потоков-индексаторов

    public static void main(String[] args) {
        File[] roots = new File[args.length];
        for (int i = 0; i < args.length; i++) {
            roots[i] = new File(args[i]);
        }
        startIndexing(roots);
    }

    public static void startIndexing(File[] args) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);
        FileFilter filter = pathname -> true;

        for (File root : args) {
            new Thread((new FileCrawler(queue, filter, root))).start();
        }

        for (int i = 0; i < N_CONSUMERS; i++) {
            new Thread((new Indexer(queue))).start();
        }
    }
}
