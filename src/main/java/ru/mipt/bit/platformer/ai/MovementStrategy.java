package ru.mipt.bit.platformer.ai;

import ru.mipt.bit.platformer.command.Runnable;
import ru.mipt.bit.platformer.model.CollisionChecker;
import ru.mipt.bit.platformer.model.entity.Player;

public interface MovementStrategy {
    Runnable nextCommand(Player player, CollisionChecker collisionChecker);
}
