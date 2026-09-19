package ru.mipt.bit.platformer.model.entity;

import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.model.Obstacle;

public class Tree implements Obstacle {

    private final GridPoint2 pos;

    public Tree(GridPoint2 pos) {
        this.pos = new GridPoint2(pos);
    }

    public GridPoint2 getPos() {
        return this.pos;
    }

    @Override
    public boolean hasCollision(GridPoint2 point) {
        return this.pos.equals(point);
    }
}
