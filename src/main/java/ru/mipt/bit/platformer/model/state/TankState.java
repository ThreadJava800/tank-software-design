package ru.mipt.bit.platformer.model.state;

import ru.mipt.bit.platformer.model.entity.Player;

/**
 * Layer: Domain. Role: State.
 */
public interface TankState {
    float movementSpeedMultiplier();
    boolean canShoot();
    void onHealthChanged(Player player);
}
