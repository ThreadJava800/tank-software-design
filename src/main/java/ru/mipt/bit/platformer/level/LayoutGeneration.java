package ru.mipt.bit.platformer.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.badlogic.gdx.math.GridPoint2;

public class LayoutGeneration {

    private final int width;
    private final int height;
    private final Random random = new Random();

    public LayoutGeneration(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public GridPoint2 pick(Set<GridPoint2> occupied) {
        GridPoint2 point;
        do {
            point = new GridPoint2(this.random.nextInt(this.width), this.random.nextInt(this.height));
        } while (!occupied.add(point));
        return point;
    }

    public List<GridPoint2> pick(int count, Set<GridPoint2> occupied) {
        List<GridPoint2> picked = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            picked.add(this.pick(occupied));
        }
        return picked;
    }
}
