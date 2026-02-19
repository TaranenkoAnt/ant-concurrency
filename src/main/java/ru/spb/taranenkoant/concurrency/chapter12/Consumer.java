package ru.spb.taranenkoant.concurrency.chapter12;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

class Consumer implements Runnable {

    private final CyclicBarrier barrier;
    private final int nTrials;
    private final BoundedBuffer<Integer> bb;
    private final AtomicInteger takeSum;

    public Consumer(CyclicBarrier barrier, int nTrials, BoundedBuffer<Integer> bb, AtomicInteger takeSum) {
        this.barrier = barrier;
        this.nTrials = nTrials;
        this.bb = bb;
        this.takeSum = takeSum;
    }

    public void run() {
        try {
            barrier.await();
            int sum = 0;
            for (int i = nTrials; i > 0; --i) {
                sum += bb.take();
            }
            takeSum.getAndAdd(sum);
            barrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}