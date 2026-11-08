package ru.mipt.bit.platformer.model.entity;

import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.model.Direction;
import ru.mipt.bit.platformer.model.Game;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Player {

    private static final float MOVEMENT_SPEED = 0.4f;
    private static final float RELOAD_TIME = 0.5f;
    private static final int HEALTH = 100;
    private static final int BULLET_DAMAGE = 25;

    private final GridPoint2 currPos, nextPos;
    private float rotation;
    private float progress;
    private Direction direction;
    private float reload;
    private int health;

    public Player(GridPoint2 pos) {
        this.currPos = new GridPoint2(pos);
        this.nextPos = new GridPoint2(pos);
        this.rotation = 0f;
        this.progress = 1f;
        this.direction = Direction.RIGHT;
        this.reload = 0f;
        this.health = HEALTH;
    }

    private boolean finishedMoving() {
        return isEqual(this.progress, 1f);
    }

    public void move(Direction direction, Game game) {
        if (this.finishedMoving()) {
            GridPoint2 newPos = new GridPoint2(this.currPos).add(direction.dx(), direction.dy());
            if (!game.hasCollision(newPos, this)) {
                this.nextPos.add(direction.dx(), direction.dy());
                this.progress = 0f;
            }
            this.rotation = direction.rotation();
            this.direction = direction;
        }
    }

    public void shoot(Game game) {
        if (!this.isAlive() || this.reload > 0f) {
            return;
        }

        this.reload = RELOAD_TIME;
        GridPoint2 bulletPos = new GridPoint2(this.nextPos).add(this.direction.dx(), this.direction.dy());
        game.addBullet(new Bullet(bulletPos, this.direction, BULLET_DAMAGE));
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void update(float deltaTime) {
        this.progress = continueProgress(this.progress, deltaTime, MOVEMENT_SPEED);
        if (this.finishedMoving()) {
            this.currPos.set(this.nextPos);
        }
        this.reload = Math.max(0f, this.reload - deltaTime);
    }

    public boolean isOccupying(GridPoint2 point) {
        return this.currPos.equals(point) || this.nextPos.equals(point);
    }

    public GridPoint2 getCurrPos() {
        return this.currPos;
    }

    public GridPoint2 getNextPos() {
        return this.nextPos;
    }

    public float getHealthRatio() {
        return Math.max(0, this.health) / (float) HEALTH;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getProgress() {
        return this.progress;
    }
}
