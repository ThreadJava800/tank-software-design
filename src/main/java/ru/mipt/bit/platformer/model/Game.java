package ru.mipt.bit.platformer.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.model.entity.Player;

public class Game {

    private Player player;
    private final List<Obstacle> obstacles = new ArrayList<>();

    public void addPlayer(Player player) {
        this.player = player;
    }

    public void addObstacle(Obstacle obstacle) {
        this.obstacles.add(obstacle);
    }

    public boolean hasCollision(GridPoint2 point) {
        for (Obstacle obstacle : this.obstacles) {
            if (obstacle.hasCollision(point)) {
                return true;
            }
        }
        return false;
    }

    public void movePlayer(Direction direction) {
        this.player.move(direction, this);
    }

    public void update(float deltaTime) {
        this.player.update(deltaTime);
    }

    public Player getPlayer() {
        return this.player;
    }

    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }
}
