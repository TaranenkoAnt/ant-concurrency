package ru.spb.taranenkoant.concurrency.chapter07;


import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 31.10.2025
 */
public class IndexingService {

    private static final File POISON = new File("");

    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;

    public IndexingService(BlockingQueue<File> queue, FileFilter fileFilter, File root) {
        this.queue = queue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    class IndexerThread  extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    File file = queue.take();
                    if (file == IndexingService.POISON) {
                        break;
                    }
                    indexFile(file);
                }
            } catch (InterruptedException e) {

            }
        }
    }

    private void indexFile(File file) {

    }

    class CrawlerThread extends Thread {

        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {

            } finally {
                while (true) {
                    try {
                        queue.put(IndexingService.POISON);
                        break;
                    } catch (InterruptedException e1) {

                    }
                }
            }
        }

        public void crawl(File root) throws InterruptedException {

        }
    }

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }

}