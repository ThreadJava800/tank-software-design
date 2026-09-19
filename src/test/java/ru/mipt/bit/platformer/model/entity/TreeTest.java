package ru.mipt.bit.platformer.model.entity;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreeTest {

    @Test
    void collidesWithSelf() {
        Tree tree = new Tree(new GridPoint2(1, 3));
        assertTrue(tree.hasCollision(new GridPoint2(1, 3)));
    }

    @Test
    void notCollidesWithOther() {
        Tree tree = new Tree(new GridPoint2(1, 3));
        assertFalse(tree.hasCollision(new GridPoint2(5, 5)));
    }
}
