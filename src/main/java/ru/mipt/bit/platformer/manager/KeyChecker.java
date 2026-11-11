package ru.mipt.bit.platformer.manager;

/**
 * Layer: Interface Adapter. Role: Output Port.
 */
public interface KeyChecker {
    boolean isPressed(int keycode);
}
