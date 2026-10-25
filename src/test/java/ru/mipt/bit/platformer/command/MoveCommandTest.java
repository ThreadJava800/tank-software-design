package ru.mipt.bit.platformer.command;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;

import ru.mipt.bit.platformer.model.Direction;
import ru.mipt.bit.platformer.model.entity.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoveCommandTest {

    @Test
    void executeMovesPlayer() {
        Player player = new Player(new GridPoint2(1, 1));
        Runnable command = new MoveCommand(player, Direction.UP, (point, mover) -> false);

        command.run();

        assertEquals(new GridPoint2(1, 2), player.getNextPos());
    }

    @Test
    void executeBlockedKeepsPlayer() {
        Player player = new Player(new GridPoint2(1, 1));
        Runnable command = new MoveCommand(player, Direction.UP, (point, mover) -> true);

        command.run();

        assertEquals(new GridPoint2(1, 1), player.getNextPos());
    }
}
