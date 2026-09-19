package ru.mipt.bit.platformer.model;

public enum Direction {
    UP(0, 1, 90f),
    LEFT(-1, 0, -180f),
    DOWN(0, -1, -90f),
    RIGHT(1, 0, 0f);

    private final int dx;
    private final int dy;
    private final float rotation;

    Direction(int dx, int dy, float rotation) {
        this.dx = dx;
        this.dy = dy;
        this.rotation = rotation;
    }

    public int dx() {
        return this.dx;
    }

    public int dy() {
        return this.dy;
    }

    public float rotation() {
        return this.rotation;
    }
}
