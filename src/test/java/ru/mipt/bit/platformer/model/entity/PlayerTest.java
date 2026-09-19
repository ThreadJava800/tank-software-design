package ru.mipt.bit.platformer.model.entity;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;

import ru.mipt.bit.platformer.model.Direction;
import ru.mipt.bit.platformer.model.Game;
import ru.mipt.bit.platformer.model.Obstacle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    private Game gameWith(Player player, Obstacle... obstacles) {
        Game game = new Game();
        game.addPlayer(player);
        for (Obstacle obstacle : obstacles) {
            game.addObstacle(obstacle);
        }
        return game;
    }

    @Test
    void idle() {
        Player player = new Player(new GridPoint2(1, 1));
        assertEquals(new GridPoint2(1, 1), player.getCurrPos());
        assertEquals(new GridPoint2(1, 1), player.getNextPos());
        assertEquals(1f, player.getProgress());
    }

    @Test
    void moveToFreeWithRotation() {
        Player player = new Player(new GridPoint2(1, 1));
        Game game = gameWith(player);

        player.move(Direction.UP, game);

        assertEquals(new GridPoint2(1, 1), player.getCurrPos());
        assertEquals(new GridPoint2(1, 2), player.getNextPos());
        assertEquals(0f, player.getProgress());
        assertEquals(90f, player.getRotation());
    }

    @Test
    void moveToBlockedWithRotation() {
        Player player = new Player(new GridPoint2(1, 1));
        Obstacle blocked = point -> point.equals(new GridPoint2(1, 2));
        Game game = gameWith(player, blocked);

        player.move(Direction.UP, game);

        assertEquals(new GridPoint2(1, 1), player.getNextPos());
        assertEquals(1f, player.getProgress());
        assertEquals(90f, player.getRotation());
    }

    @Test
    void moveDuringMove() {
        Player player = new Player(new GridPoint2(1, 1));
        Game game = gameWith(player);

        player.move(Direction.UP, game);
        player.move(Direction.RIGHT, game);

        assertEquals(new GridPoint2(1, 2), player.getNextPos());
        assertEquals(90f, player.getRotation());
    }
}
