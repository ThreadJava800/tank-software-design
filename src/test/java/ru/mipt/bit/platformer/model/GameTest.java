package ru.mipt.bit.platformer.model;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;

import ru.mipt.bit.platformer.ai.MovementStrategy;
import ru.mipt.bit.platformer.command.MoveCommand;
import ru.mipt.bit.platformer.model.entity.Player;
import ru.mipt.bit.platformer.model.entity.Tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 8;

    @Test
    void treeBlocksCell() {
        Game game = new Game(WIDTH, HEIGHT);
        game.addObstacle(new Tree(new GridPoint2(1, 3)));

        assertTrue(game.hasCollision(new GridPoint2(1, 3), null));
        assertFalse(game.hasCollision(new GridPoint2(2, 2), null));
    }

    @Test
    void levelBoundsBlockCell() {
        Game game = new Game(WIDTH, HEIGHT);

        assertTrue(game.hasCollision(new GridPoint2(-1, 0), null));
        assertTrue(game.hasCollision(new GridPoint2(0, HEIGHT), null));
        assertFalse(game.hasCollision(new GridPoint2(0, 0), null));
    }

    @Test
    void anotherPlayerBlocksCell() {
        Game game = new Game(WIDTH, HEIGHT);
        Player player = new Player(new GridPoint2(1, 1));
        Player enemy = new Player(new GridPoint2(2, 2));
        game.addPlayer(player);
        game.addAiPlayer(enemy, (aiPlayer, checker) -> new MoveCommand(aiPlayer, Direction.UP, checker));

        assertTrue(game.hasCollision(new GridPoint2(2, 2), player));
        assertFalse(game.hasCollision(new GridPoint2(2, 2), enemy));
    }

    @Test
    void playerStopsAtLevelBound() {
        Game game = new Game(WIDTH, HEIGHT);
        Player player = new Player(new GridPoint2(0, 0));
        game.addPlayer(player);

        player.move(Direction.LEFT, game);

        assertEquals(new GridPoint2(0, 0), player.getNextPos());
    }

    @Test
    void updateDrivesAiPlayer() {
        Game game = new Game(WIDTH, HEIGHT);
        Player enemy = new Player(new GridPoint2(1, 1));
        game.addAiPlayer(enemy, (player, checker) -> new MoveCommand(player, Direction.UP, checker));

        game.update(1f);

        assertEquals(new GridPoint2(1, 2), enemy.getCurrPos());
    }

    @Test
    void updateAdvancesPlayer() {
        Game game = new Game(WIDTH, HEIGHT);
        Player player = new Player(new GridPoint2(1, 1));
        game.addPlayer(player);

        new MoveCommand(player, Direction.UP, game).run();
        game.update(1f);

        assertEquals(new GridPoint2(1, 2), player.getCurrPos());
    }

    @Test
    void aiPlayersDoNotShareCell() {
        Game game = new Game(WIDTH, HEIGHT);
        Player first = new Player(new GridPoint2(1, 1));
        Player second = new Player(new GridPoint2(1, 3));
        game.addAiPlayer(first, (player, checker) -> new MoveCommand(player, Direction.UP, checker));
        game.addAiPlayer(second, (player, checker) -> new MoveCommand(player, Direction.DOWN, checker));

        game.update(1f);

        assertEquals(new GridPoint2(1, 2), first.getCurrPos());
        assertEquals(new GridPoint2(1, 3), second.getCurrPos());
    }
}
