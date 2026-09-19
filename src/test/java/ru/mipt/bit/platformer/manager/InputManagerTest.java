package ru.mipt.bit.platformer.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputManagerTest {

    private static final int KEY_A = 1;
    private static final int KEY_B = 2;
    private static final int KEY_C = 3;

    @Test
    void checkHandlerCorrectKey() {
        Set<Integer> pressed = new HashSet<>();
        pressed.add(KEY_C);
        boolean[] fired = {false};
        InputManager manager = new InputManager(pressed::contains);
        manager.register(KEY_C, () -> fired[0] = true);

        manager.handleInput();

        assertTrue(fired[0]);
    }

    @Test
    void checkHandlerNoKey() {
        Set<Integer> pressed = new HashSet<>();
        int[] runs = {0};
        InputManager manager = new InputManager(pressed::contains);
        manager.register(KEY_A, () -> runs[0]++);

        manager.handleInput();

        assertEquals(0, runs[0]);
    }

    @Test
    void checkHandlerWrongKey() {
        Set<Integer> pressed = new HashSet<>();
        pressed.add(KEY_B);
        int[] runs = {0};
        InputManager manager = new InputManager(pressed::contains);
        manager.register(KEY_A, () -> runs[0]++);

        manager.handleInput();

        assertEquals(0, runs[0]);
    }

    @Test
    void checkOrder() {
        Set<Integer> pressed = new HashSet<>();
        pressed.add(KEY_A);
        pressed.add(KEY_B);
        List<Integer> order = new ArrayList<>();
        InputManager manager = new InputManager(pressed::contains);
        manager.register(KEY_A, () -> order.add(KEY_A));
        manager.register(KEY_B, () -> order.add(KEY_B));

        manager.handleInput();

        assertEquals(List.of(KEY_A, KEY_B), order);
    }
}
