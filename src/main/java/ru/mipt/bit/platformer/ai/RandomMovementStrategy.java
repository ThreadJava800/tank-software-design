package ru.mipt.bit.platformer.ai;

import java.util.Random;

import ru.mipt.bit.platformer.command.Runnable;
import ru.mipt.bit.platformer.command.MoveCommand;
import ru.mipt.bit.platformer.command.ShootCommand;
import ru.mipt.bit.platformer.model.Direction;
import ru.mipt.bit.platformer.model.Game;
import ru.mipt.bit.platformer.model.entity.Player;

public class RandomMovementStrategy implements MovementStrategy {

    private static final int SHOOT_CHANCE = 100;

    private final Random random = new Random();

    @Override
    public Runnable nextCommand(Player player, Game game) {
        if (this.random.nextInt(SHOOT_CHANCE) == 0) {
            return new ShootCommand(player, game);
        }
        Direction direction = Direction.values()[this.random.nextInt(Direction.values().length)];
        return new MoveCommand(player, direction, game);
    }
}
