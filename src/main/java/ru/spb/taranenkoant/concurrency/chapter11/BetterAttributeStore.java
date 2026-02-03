package ru.spb.taranenkoant.concurrency.chapter11;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 04.12.2025
 */
public class BetterAttributeStore {

    private final Map<String, String> attributes = new HashMap<>();

    public boolean userLocationMatches(String name, String regexp) {
        String key = "users." + name + ".location";
        String location;
        synchronized (this) {
            location = attributes.get(key);
        }
        if (location == null) {
            return false;
        } else {
            return Pattern.matches(regexp, location);
        }
    }
}
