package ru.mipt.bit.platformer.objects.obstacles;

import com.badlogic.gdx.math.GridPoint2;

public class Tree implements Obstacle {
    private final GridPoint2 pos;

    public Tree(GridPoint2 pos) {
        this.pos = pos;
    }

    @Override
    public boolean contains(GridPoint2 point) {
        return this.pos == point;
    }
}
