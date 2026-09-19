package ru.mipt.bit.platformer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {

    @Test
    void up() {
        assertEquals(0, Direction.UP.dx());
        assertEquals(1, Direction.UP.dy());
        assertEquals(90f, Direction.UP.rotation());
    }

    @Test
    void left() {
        assertEquals(-1, Direction.LEFT.dx());
        assertEquals(0, Direction.LEFT.dy());
        assertEquals(-180f, Direction.LEFT.rotation());
    }

    @Test
    void down() {
        assertEquals(0, Direction.DOWN.dx());
        assertEquals(-1, Direction.DOWN.dy());
        assertEquals(-90f, Direction.DOWN.rotation());
    }

    @Test
    void right() {
        assertEquals(1, Direction.RIGHT.dx());
        assertEquals(0, Direction.RIGHT.dy());
        assertEquals(0f, Direction.RIGHT.rotation());
    }
}
