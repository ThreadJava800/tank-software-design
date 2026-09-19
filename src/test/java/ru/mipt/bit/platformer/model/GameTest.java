package ru.mipt.bit.platformer.model;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;

import ru.mipt.bit.platformer.model.entity.Player;
import ru.mipt.bit.platformer.model.entity.Tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {

    private Game gameWithTreeAt(Player player, GridPoint2 treePos) {
        Game game = new Game();
        game.addPlayer(player);
        game.addObstacle(new Tree(treePos));
        return game;
    }

    @Test
    void staleNoTree() {
        Player player = new Player(new GridPoint2(1, 1));
        Game game = gameWithTreeAt(player, new GridPoint2(1, 3));

        assertTrue(game.hasCollision(new GridPoint2(1, 3)));
        assertFalse(game.hasCollision(new GridPoint2(2, 2)));
    }

    @Test
    void moveToFree() {
        Player player = new Player(new GridPoint2(1, 1));
        Game game = gameWithTreeAt(player, new GridPoint2(1, 3));

        game.movePlayer(Direction.UP);

        assertEquals(new GridPoint2(1, 2), player.getNextPos());

        game.update(1f);
        assertEquals(new GridPoint2(1, 2), player.getCurrPos());
    }

    @Test
    void moveToObstacle() {
        Player player = new Player(new GridPoint2(1, 2));
        Game game = gameWithTreeAt(player, new GridPoint2(1, 3));

        game.movePlayer(Direction.UP);

        assertEquals(new GridPoint2(1, 2), player.getNextPos());
    }

    @Test
    void updateDelegatesToPlayer() {
        Player player = new Player(new GridPoint2(1, 1));
        Game game = gameWithTreeAt(player, new GridPoint2(1, 3));

        game.movePlayer(Direction.UP);
        game.update(1f);

        assertEquals(new GridPoint2(1, 2), player.getCurrPos());
    }
}
