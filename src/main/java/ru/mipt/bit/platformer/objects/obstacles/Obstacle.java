package ru.mipt.bit.platformer.objects.obstacles;

import com.badlogic.gdx.math.GridPoint2;

public interface Obstacle {
    boolean contains(GridPoint2 point);
}
