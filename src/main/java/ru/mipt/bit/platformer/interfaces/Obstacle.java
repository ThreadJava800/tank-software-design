package ru.mipt.bit.platformer.interfaces;

import com.badlogic.gdx.math.GridPoint2;

public interface Obstacle {
    boolean contains(GridPoint2 point);
}
