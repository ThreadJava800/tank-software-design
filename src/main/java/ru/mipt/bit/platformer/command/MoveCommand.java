package ru.mipt.bit.platformer.command;

import ru.mipt.bit.platformer.model.CollisionChecker;
import ru.mipt.bit.platformer.model.Direction;
import ru.mipt.bit.platformer.model.entity.Player;

public class MoveCommand implements Runnable {

    private final Player player;
    private final Direction direction;
    private final CollisionChecker collisionChecker;

    public MoveCommand(Player player, Direction direction, CollisionChecker collisionChecker) {
        this.player = player;
        this.direction = direction;
        this.collisionChecker = collisionChecker;
    }

    @Override
    public void run() {
        this.player.move(this.direction, this.collisionChecker);
    }
}
