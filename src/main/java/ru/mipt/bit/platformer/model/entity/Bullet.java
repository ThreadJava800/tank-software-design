package ru.mipt.bit.platformer.model.entity;

import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.model.Direction;
import ru.mipt.bit.platformer.model.Game;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

/**
 * Layer: Domain. Role: Entity.
 */
public class Bullet {

    private static final float MOVEMENT_SPEED = 0.1f;

    private final GridPoint2 currPos, nextPos;
    private final Direction direction;
    private final int damage;
    private float progress;
    private boolean destroyed;

    public Bullet(GridPoint2 pos, Direction direction, int damage) {
        this.currPos = new GridPoint2(pos);
        this.nextPos = new GridPoint2(pos);
        this.direction = direction;
        this.damage = damage;
        this.progress = 1f;
        this.destroyed = false;
    }

    public void update(float deltaTime, Game game) {
        if (this.destroyed) {
            return;
        }

        this.progress = continueProgress(this.progress, deltaTime, MOVEMENT_SPEED);
        if (!isEqual(this.progress, 1f)) {
            return;
        }

        this.currPos.set(this.nextPos);

        GridPoint2 newPos = new GridPoint2(this.currPos).add(this.direction.dx(), this.direction.dy());
        if (game.hasCollision(newPos, null)) {
            this.destroyed = true;
            game.hit(newPos, this.damage);
        } else {
            this.nextPos.set(newPos);
            this.progress = 0f;
        }
    }

    public void destroy() {
        this.destroyed = true;
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public boolean isOccupying(GridPoint2 point) {
        return !this.destroyed && (this.currPos.equals(point) || this.nextPos.equals(point));
    }

    public GridPoint2 getCurrPos() {
        return this.currPos;
    }

    public GridPoint2 getNextPos() {
        return this.nextPos;
    }

    public float getRotation() {
        return this.direction.rotation();
    }

    public float getProgress() {
        return this.progress;
    }

    public int getDamage() {
        return this.damage;
    }
}
