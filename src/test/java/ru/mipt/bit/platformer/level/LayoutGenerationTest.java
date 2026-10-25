package ru.mipt.bit.platformer.level;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LayoutGenerationTest {

    @Test
    void picksDistinctPointsWithinBounds() {
        LayoutGeneration positions = new LayoutGeneration(4, 4);
        Set<GridPoint2> occupied = new HashSet<>();

        List<GridPoint2> picked = positions.pick(5, occupied);

        assertEquals(5, picked.size());
        assertEquals(5, new HashSet<>(picked).size());
        for (GridPoint2 point : picked) {
            assertTrue(point.x >= 0 && point.x < 4 && point.y >= 0 && point.y < 4);
        }
    }

    @Test
    void avoidsOccupiedPoints() {
        LayoutGeneration positions = new LayoutGeneration(2, 2);
        Set<GridPoint2> occupied = new HashSet<>();
        occupied.add(new GridPoint2(0, 0));
        occupied.add(new GridPoint2(0, 1));
        occupied.add(new GridPoint2(1, 0));

        GridPoint2 point = positions.pick(occupied);

        assertEquals(new GridPoint2(1, 1), point);
        assertFalse(point.equals(new GridPoint2(0, 0)));
    }
}
