package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;

import ru.mipt.bit.platformer.ObstacleManager;
import ru.mipt.bit.platformer.interfaces.RenderContext;
import ru.mipt.bit.platformer.interfaces.Renderable;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Player implements Renderable {

    private enum MoveContext {
        UP(0, 1, 90f),
        LEFT(-1, 0, -180f),
        DOWN(0, -1, -90f),
        RIGHT(1, 0, 0f);

        private final int dx;
        private final int dy;
        private final float rotation;

        MoveContext(int dx, int dy, float rotation) {
            this.dx = dx;
            this.dy = dy;
            this.rotation = rotation;
        }

        public int dx() {
            return this.dx;
        }

        public int dy() {
            return this.dy;
        }

        public float rotation() {
            return this.rotation;
        }
    }

    private static final float MOVEMENT_SPEED = 0.4f;

    private TileMovement tileMovement;
    private Texture texture;
    private TextureRegion graphics;
    private Rectangle rectangle;

    private GridPoint2 currPos, nextPos;
    private float rotation;
    private float progress;

    public Player(TiledMapTileLayer groundLayer, GridPoint2 pos) {
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        this.texture = new Texture("images/tank_blue.png");
        this.graphics = new TextureRegion(this.texture);
        this.rectangle = createBoundingRectangle(this.graphics);

        this.currPos = new GridPoint2(pos);
        this.nextPos = new GridPoint2(pos);
        this.rotation = 0f;
        this.progress = 1f;
    }

    private static MoveContext getMoveContext() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            return MoveContext.UP;
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            return MoveContext.LEFT;
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return MoveContext.DOWN;
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return MoveContext.RIGHT;
        }
        return null;
    }

    private boolean finishedMoving() {
        return isEqual(this.progress, 1f);
    }

    private void updatePos(float deltaTime) {
        MoveContext moveCtx = getMoveContext();
        if (moveCtx != null) {
            if (this.finishedMoving()) {
                GridPoint2 newPos = new GridPoint2(this.currPos).add(moveCtx.dx(), moveCtx.dy());
                if (!ObstacleManager.getInstance().contains(newPos)) {
                    this.nextPos.add(moveCtx.dx(), moveCtx.dy());
                    this.progress = 0f;
                }
                this.rotation = moveCtx.rotation();
            }
        }

        // calculate interpolated player screen coordinates
        this.tileMovement.moveRectangleBetweenTileCenters(this.rectangle, this.currPos, this.nextPos, this.progress);

        this.progress = continueProgress(this.progress, deltaTime, MOVEMENT_SPEED);
        if (this.finishedMoving()) {
            // record that the player has reached his/her destination
            this.currPos.set(this.nextPos);
        }
    }

    @Override
    public void render(RenderContext context) {
        this.updatePos(context.deltaTime);
        drawTextureRegionUnscaled(context.batch, this.graphics, this.rectangle, this.rotation);
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }
}
