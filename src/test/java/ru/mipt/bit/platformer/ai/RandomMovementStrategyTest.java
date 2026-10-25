package ru.mipt.bit.platformer.ai;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;

import ru.mipt.bit.platformer.command.Runnable;
import ru.mipt.bit.platformer.model.entity.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomMovementStrategyTest {

    @Test
    void movesPlayerToNeighbourCell() {
        MovementStrategy strategy = new RandomMovementStrategy();
        Player player = new Player(new GridPoint2(5, 5));

        Runnable command = strategy.nextCommand(player, (point, mover) -> false);
        command.run();

        GridPoint2 next = player.getNextPos();
        int distance = Math.abs(next.x - 5) + Math.abs(next.y - 5);
        assertEquals(1, distance);
    }
}
