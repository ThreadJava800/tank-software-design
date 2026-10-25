package ru.mipt.bit.platformer.ai;

import java.util.Random;

import ru.mipt.bit.platformer.command.Runnable;
import ru.mipt.bit.platformer.command.MoveCommand;
import ru.mipt.bit.platformer.model.CollisionChecker;
import ru.mipt.bit.platformer.model.Direction;
import ru.mipt.bit.platformer.model.entity.Player;

public class RandomMovementStrategy implements MovementStrategy {

    private final Random random = new Random();

    @Override
    public Runnable nextCommand(Player player, CollisionChecker collisionChecker) {
        Direction direction = Direction.values()[this.random.nextInt(Direction.values().length)];
        return new MoveCommand(player, direction, collisionChecker);
    }
}
