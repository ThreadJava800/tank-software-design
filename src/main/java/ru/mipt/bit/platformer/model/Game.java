package ru.mipt.bit.platformer.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.ai.MovementStrategy;
import ru.mipt.bit.platformer.model.entity.Bullet;
import ru.mipt.bit.platformer.model.entity.Player;

public class Game {

    private final int width;
    private final int height;

    private Player player;
    private final List<Player> players = new ArrayList<>();
    private final Map<Player, MovementStrategy> aiPlayers = new LinkedHashMap<>();
    private final List<Obstacle> obstacles = new ArrayList<>();
    private final List<Bullet> bullets = new ArrayList<>();
    private final List<LevelListener> listeners = new ArrayList<>();

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addListener(LevelListener listener) {
        this.listeners.add(listener);
    }

    public void addPlayer(Player player) {
        this.player = player;
        this.players.add(player);
        this.notifyAdded(player);
    }

    public void addAiPlayer(Player player, MovementStrategy strategy) {
        this.players.add(player);
        this.aiPlayers.put(player, strategy);
        this.notifyAdded(player);
    }

    public void addObstacle(Obstacle obstacle) {
        this.obstacles.add(obstacle);
        this.notifyAdded(obstacle);
    }

    public void addBullet(Bullet bullet) {
        if (this.hasCollision(bullet.getCurrPos(), null)) {
            this.hit(bullet.getCurrPos(), bullet.getDamage());
            return;
        }

        this.bullets.add(bullet);
        this.notifyAdded(bullet);
    }

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
        for (Bullet bullet : this.bullets) {
            if (bullet.isOccupying(point)) {
                return true;
            }
        }
        return false;
    }

    public void hit(GridPoint2 point, int damage) {
        for (Player player : this.players) {
            if (player.isOccupying(point)) {
                player.takeDamage(damage);
            }
        }
        for (Bullet bullet : this.bullets) {
            if (bullet.isOccupying(point)) {
                bullet.destroy();
            }
        }
    }

    public void update(float deltaTime) {
        for (Map.Entry<Player, MovementStrategy> entry : this.aiPlayers.entrySet()) {
            entry.getValue().nextCommand(entry.getKey(), this).run();
        }
        for (Player player : this.players) {
            player.update(deltaTime);
        }
        for (Bullet bullet : this.bullets) {
            bullet.update(deltaTime, this);
        }
        this.removeDestroyed();
    }

    private void removeDestroyed() {
        for (Player player : new ArrayList<>(this.players)) {
            if (!player.isAlive()) {
                this.players.remove(player);
                this.aiPlayers.remove(player);
                this.notifyRemoved(player);
            }
        }
        for (Bullet bullet : new ArrayList<>(this.bullets)) {
            if (bullet.isDestroyed()) {
                this.bullets.remove(bullet);
                this.notifyRemoved(bullet);
            }
        }
    }

    private void notifyAdded(Object object) {
        for (LevelListener listener : this.listeners) {
            listener.onAdded(object);
        }
    }

    private void notifyRemoved(Object object) {
        for (LevelListener listener : this.listeners) {
            listener.onRemoved(object);
        }
    }

    private boolean isOutOfBounds(GridPoint2 point) {
        return point.x < 0 || point.y < 0 || point.x >= this.width || point.y >= this.height;
    }

    public Player getPlayer() {
        return this.player;
    }
}
