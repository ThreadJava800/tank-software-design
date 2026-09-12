package ru.mipt.bit.platformer;

import java.util.ArrayList;

import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.interfaces.Obstacle;

public class ObstacleManager {
    private static ObstacleManager instance;

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

    public static ObstacleManager getInstance() {
        if (instance == null)
            instance = new ObstacleManager();
        return instance;
    }
}
