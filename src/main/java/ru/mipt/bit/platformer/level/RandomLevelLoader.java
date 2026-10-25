package ru.mipt.bit.platformer.level;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.math.GridPoint2;

public class RandomLevelLoader implements LevelLoader {

    private final int width;
    private final int height;
    private final int obstacleCount;

    public RandomLevelLoader(int width, int height, int obstacleCount) {
        this.width = width;
        this.height = height;
        this.obstacleCount = obstacleCount;
    }

    @Override
    public LevelLayout load() {
        LayoutGeneration positions = new LayoutGeneration(this.width, this.height);
        Set<GridPoint2> occupied = new HashSet<>();

        GridPoint2 playerPosition = positions.pick(occupied);
        List<GridPoint2> treePositions = positions.pick(this.obstacleCount, occupied);

        return new LevelLayout(playerPosition, treePositions);
    }
}
