package ru.mipt.bit.platformer.level;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.badlogic.gdx.math.GridPoint2;

public class RandomLevelLoader implements LevelLoader {

    private final int width;
    private final int height;
    private final int obstacleCount;
    private final Random random = new Random();

    public RandomLevelLoader(int width, int height, int obstacleCount) {
        this.width = width;
        this.height = height;
        this.obstacleCount = obstacleCount;
    }

    @Override
    public LevelLayout load() {
        Set<GridPoint2> occupied = new HashSet<>();

        GridPoint2 playerPosition = this.randomFreePoint(occupied);

        List<GridPoint2> treePositions = new ArrayList<>();
        for (int i = 0; i < this.obstacleCount; i++) {
            treePositions.add(this.randomFreePoint(occupied));
        }

        return new LevelLayout(playerPosition, treePositions);
    }

    private GridPoint2 randomFreePoint(Set<GridPoint2> occupied) {
        GridPoint2 point;
        do {
            point = new GridPoint2(this.random.nextInt(this.width), this.random.nextInt(this.height));
        } while (!occupied.add(point));
        return point;
    }
}
