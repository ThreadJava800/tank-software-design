package ru.mipt.bit.platformer.command;

import ru.mipt.bit.platformer.model.Game;
import ru.mipt.bit.platformer.model.entity.Player;

/**
 * Layer: Application. Role: Use Case.
 */
public class ShootCommand implements Runnable {

    private final Player player;
    private final Game game;

    public ShootCommand(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void run() {
        this.player.shoot(this.game);
    }
}
