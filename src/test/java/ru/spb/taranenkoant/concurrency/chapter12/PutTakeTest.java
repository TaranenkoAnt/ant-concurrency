package ru.spb.taranenkoant.concurrency.chapter12;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class PutTakeTest {

    private ExecutorService pool;
    private final AtomicInteger putSum = new AtomicInteger(0);
    private final AtomicInteger takeSum = new AtomicInteger(0);
    private  CyclicBarrier barrier;
    private  BoundedBuffer<Integer> bb;
    private  int nTrials, nPairs;

    @BeforeEach
    public void init() {
        this.pool = Executors.newCachedThreadPool();
        this.bb = new BoundedBuffer<Integer>(10);
        this.nTrials = 10;
        this.nPairs = 10000;
        this.barrier = new CyclicBarrier(nPairs * 2 + 1);
    }

    @Test
    void test() {
        try {
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new Producer(barrier, nTrials, bb, putSum));
                pool.execute(new Consumer(barrier, nTrials, bb, takeSum));
            }
            barrier.await();
            barrier.await();
            assertEquals(putSum.get(), takeSum.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void finalize() {
        pool.shutdown();
    }
}