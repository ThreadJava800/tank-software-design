package ru.mipt.bit.platformer.model.entity;

import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.model.Direction;
import ru.mipt.bit.platformer.model.Game;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Player {

    private static final float MOVEMENT_SPEED = 0.4f;

    private final GridPoint2 currPos, nextPos;
    private float rotation;
    private float progress;

    public Player(GridPoint2 pos) {
        this.currPos = new GridPoint2(pos);
        this.nextPos = new GridPoint2(pos);
        this.rotation = 0f;
        this.progress = 1f;
    }

    private boolean finishedMoving() {
        return isEqual(this.progress, 1f);
    }

    public void move(Direction direction, Game game) {
        if (this.finishedMoving()) {
            GridPoint2 newPos = new GridPoint2(this.currPos).add(direction.dx(), direction.dy());
            if (!game.hasCollision(newPos)) {
                this.nextPos.add(direction.dx(), direction.dy());
                this.progress = 0f;
            }
            this.rotation = direction.rotation();
        }
    }

    public void update(float deltaTime) {
        this.progress = continueProgress(this.progress, deltaTime, MOVEMENT_SPEED);
        if (this.finishedMoving()) {
            this.currPos.set(this.nextPos);
        }
    }

    public GridPoint2 getCurrPos() {
        return this.currPos;
    }

    public GridPoint2 getNextPos() {
        return this.nextPos;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getProgress() {
        return this.progress;
    }
}
