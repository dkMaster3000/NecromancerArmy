package main.modelsview.resources;

import main.models.undead.Undead;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DefaultUndeadCollection {

    private final static List<Undead> DEFAULT_UNDEADS;

    static {
        List<Undead> undeads = new ArrayList<>();
        for (DefaultUndead defaultUndead : DefaultUndead.values()) {
            undeads.add(defaultUndead.getUndead());
        }
        DEFAULT_UNDEADS = Collections.unmodifiableList(undeads);
    }

    private DefaultUndeadCollection() {
        // Prevent instantiation
    }

    public static List<Undead> getDefaultUndeads() {
        return DEFAULT_UNDEADS;
    }
}

