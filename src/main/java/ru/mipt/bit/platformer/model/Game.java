package ru.mipt.bit.platformer.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.ai.MovementStrategy;
import ru.mipt.bit.platformer.model.entity.Player;

public class Game implements CollisionChecker {

    private final int width;
    private final int height;

    private Player player;
    private final List<Player> players = new ArrayList<>();
    private final Map<Player, MovementStrategy> aiPlayers = new LinkedHashMap<>();
    private final List<Obstacle> obstacles = new ArrayList<>();

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addPlayer(Player player) {
        this.player = player;
        this.players.add(player);
    }

    public void addAiPlayer(Player player, MovementStrategy strategy) {
        this.players.add(player);
        this.aiPlayers.put(player, strategy);
    }

    public void addObstacle(Obstacle obstacle) {
        this.obstacles.add(obstacle);
    }

    @Override
    public boolean hasCollision(GridPoint2 point, Player gamer) {
        if (this.isOutOfBounds(point)) {
            return true;
        }
        for (Obstacle obstacle : this.obstacles) {
            if (obstacle.hasCollision(point)) {
                return true;
            }
        }
        for (Player player : this.players) {
            if (player != gamer && player.isOccupying(point)) {
                return true;
            }
        }
        return false;
    }

    public void update(float deltaTime) {
        for (Map.Entry<Player, MovementStrategy> entry : this.aiPlayers.entrySet()) {
            entry.getValue().nextCommand(entry.getKey(), this).run();
        }
        for (Player player : this.players) {
            player.update(deltaTime);
        }
    }

    private boolean isOutOfBounds(GridPoint2 point) {
        return point.x < 0 || point.y < 0 || point.x >= this.width || point.y >= this.height;
    }

    public Player getPlayer() {
        return this.player;
    }
}
