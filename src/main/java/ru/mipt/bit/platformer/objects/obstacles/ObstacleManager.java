package ru.mipt.bit.platformer.objects.obstacles;

import java.util.ArrayList;

import com.badlogic.gdx.math.GridPoint2;

public class ObstacleManager {
    private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

    public void addObstacle(Obstacle obstacle) {
        this.obstacles.add(obstacle);
    }

    public boolean contains(GridPoint2 point) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.contains(point))
                return true;
        }
        return false;
    }
}
