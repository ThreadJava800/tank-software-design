package ru.mipt.bit.platformer.command;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;

import ru.mipt.bit.platformer.model.Game;
import ru.mipt.bit.platformer.model.Direction;
import ru.mipt.bit.platformer.model.entity.Player;
import ru.mipt.bit.platformer.model.entity.Tree;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoveCommandTest {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 8;

    @Test
    void executeMovesPlayer() {
        Game game = new Game(WIDTH, HEIGHT);
        Player player = new Player(new GridPoint2(1, 1));
        Runnable command = new MoveCommand(player, Direction.UP, game);

        game.addPlayer(player);

        command.run();

        assertEquals(new GridPoint2(1, 2), player.getNextPos());
    }

    @Test
    void executeBlockedKeepsPlayer() {
        Game game = new Game(WIDTH, HEIGHT);
        Player player = new Player(new GridPoint2(1, 1));
        Tree tree = new Tree(new GridPoint2(1, 2));
        Runnable command = new MoveCommand(player, Direction.UP, game);

        game.addPlayer(player);
        game.addObstacle(tree);

        command.run();

        assertEquals(new GridPoint2(1, 1), player.getNextPos());
    }
}
