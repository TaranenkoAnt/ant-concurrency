package ru.spb.taranenkoant.concurrency.chapter10;


/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 01.12.2025
 */
public class BlockingOrder {

    private static final Object tieLock = new Object();

    public void transferMoney(final Account from,
                              final Account to,
                              final DollarAmount amount) throws InsufficentFundsException {
        class Helper {
            public void transfer() throws InsufficentFundsException {
                if (from.getBalance().compareTo(amount) < 0) {
                    throw new InsufficentFundsException();
                } else {
                    from.debit(amount);
                    to.credit(amount);
                }
            }
        }

        int fronHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        if (fronHash < toHash) {
            synchronized (from) {
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        } else if (fronHash > toHash) {
            synchronized (to) {
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (from) {
                    synchronized (to) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }
}
