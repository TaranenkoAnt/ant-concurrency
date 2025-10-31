package ru.spb.taranenkoant.concurrency.chapter07;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 31.10.2025
 */
public class UEHLogger implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "Поток терминирован с исключением: " + t.getName(), e);
    }
}
