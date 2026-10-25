package ru.mipt.bit.platformer.model;

import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.model.entity.Player;

public interface CollisionChecker {
    boolean hasCollision(GridPoint2 point, Player gamer);
}
