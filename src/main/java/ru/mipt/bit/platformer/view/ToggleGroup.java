package ru.mipt.bit.platformer.view;

import java.util.LinkedHashMap;
import java.util.Map;

public class ToggleGroup implements Toggleable {

    private final Map<Object, Toggleable> toggleables = new LinkedHashMap<>();

    public void add(Object key, Toggleable toggleable) {
        this.toggleables.put(key, toggleable);
    }

    public void remove(Object key) {
        this.toggleables.remove(key);
    }

    @Override
    public void toggle() {
        for (Toggleable toggleable : this.toggleables.values()) {
            toggleable.toggle();
        }
    }
}
