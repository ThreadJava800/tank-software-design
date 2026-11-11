package ru.mipt.bit.platformer.model.state;

import ru.mipt.bit.platformer.model.entity.Player;

/**
 * Layer: Domain. Role: State.
 */
public class MediumState implements TankState {

    private static final float MIN_RATIO = 0.15f;

    @Override
    public float movementSpeedMultiplier() {
        return 0.5f;
    }

    @Override
    public boolean canShoot() {
        return true;
    }

    @Override
    public void onHealthChanged(Player player) {
        if (player.getHealthRatio() < MIN_RATIO) {
            TankState next = new HeavyState();
            player.setState(next);
            next.onHealthChanged(player);
        }
    }
}
