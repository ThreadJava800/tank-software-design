package ru.mipt.bit.platformer.model.state;

import ru.mipt.bit.platformer.model.entity.Player;

/**
 * Layer: Domain. Role: State.
 */
public class HeavyState implements TankState {

    @Override
    public float movementSpeedMultiplier() {
        return 1f / 3f;
    }

    @Override
    public boolean canShoot() {
        return false;
    }

    @Override
    public void onHealthChanged(Player player) {
    }
}
