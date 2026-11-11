package ru.mipt.bit.platformer.model;

/**
 * Layer: Application. Role: Output Port.
 */
public interface LevelListener {
    void onAdded(Object object);
    void onRemoved(Object object);
}
