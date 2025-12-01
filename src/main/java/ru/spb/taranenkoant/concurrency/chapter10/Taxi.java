package ru.spb.taranenkoant.concurrency.chapter10;


import ru.spb.taranenkoant.concurrency.chapter4.Point;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 01.12.2025
 */
public class Taxi {

    private Point location, destination;
    private final Dispatcher dispatcher;

    public Taxi(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public synchronized Point getLocation() {
        return location;
    }

    public synchronized void setLocation(Point location) {
        boolean reachedDestination;
        synchronized (this) {
            this.location = location;
            reachedDestination = location.equals(destination);
        }
        if (reachedDestination) {
            dispatcher.notifyAvailable(this);
        }
    }
}
