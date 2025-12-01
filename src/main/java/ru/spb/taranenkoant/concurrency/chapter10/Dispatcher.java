package ru.spb.taranenkoant.concurrency.chapter10;


import java.util.HashSet;
import java.util.Set;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 01.12.2025
 */
public class Dispatcher {
    private final Set<Taxi> taxis;
    private final Set<Taxi> availableTaxis;

    public Dispatcher(Set<Taxi> taxis, Set<Taxi> availableTaxis) {
        this.taxis = taxis;
        this.availableTaxis = availableTaxis;
    }

    public synchronized void notifyAvailable(Taxi taxi) {
        availableTaxis.add(taxi);
    }

    public Image getImage() {
        Set<Taxi> copy;
        synchronized (this) {
            copy = new HashSet<>(taxis);
        }
        Image image = new Image();
        for (Taxi t : copy) {
            image.drawMaker(t.getLocation());
        }
        return image;
    }
}
