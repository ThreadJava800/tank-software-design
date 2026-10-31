package ru.mipt.bit.platformer.command;

import ru.mipt.bit.platformer.model.Game;
import ru.mipt.bit.platformer.model.Direction;
import ru.mipt.bit.platformer.model.entity.Player;

public class MoveCommand implements Runnable {

    private final Player player;
    private final Direction direction;
    private final Game game;

    public MoveCommand(Player player, Direction direction, Game game) {
        this.player = player;
        this.direction = direction;
        this.game = game;
    }

    @Override
    public void run() {
        this.player.move(this.direction, this.game);
    }
}
