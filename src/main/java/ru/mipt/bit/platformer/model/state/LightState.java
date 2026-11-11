package ru.mipt.bit.platformer.model.state;

import ru.mipt.bit.platformer.model.entity.Player;

/**
 * Layer: Domain. Role: State.
 */
public class LightState implements TankState {

    private static final float MAX_RATIO = 0.7f;

    @Override
    public float movementSpeedMultiplier() {
        return 1f;
    }

    @Override
    public boolean canShoot() {
        return true;
    }

    @Override
    public void onHealthChanged(Player player) {
        if (player.getHealthRatio() <= MAX_RATIO) {
            TankState next = new MediumState();
            player.setState(next);
            next.onHealthChanged(player);
        }
    }
}
