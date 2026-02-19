package ru.spb.taranenkoant.concurrency.chapter12;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

class Producer implements Runnable {

    private final CyclicBarrier barrier;
    private final int nTrials;
    private final BoundedBuffer<Integer> bb;
    private final AtomicInteger putSum;

    public Producer(CyclicBarrier barrier, int nTrials, BoundedBuffer<Integer> bb, AtomicInteger putSum) {
        this.barrier = barrier;
        this.nTrials = nTrials;
        this.bb = bb;
        this.putSum = putSum;
    }

    public void run() {
        try {
            int seed = (this.hashCode() ^ (int) System.nanoTime());
            int sum = 0;
            barrier.await();
            for (int i = nTrials; i > 0; --i) {
                bb.put(seed);
                sum += seed;
                seed = xorShift(seed);
            }
            putSum.getAndAdd(sum);
            barrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }
}