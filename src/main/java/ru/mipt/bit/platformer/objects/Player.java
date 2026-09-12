package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;

import ru.mipt.bit.platformer.interfaces.RenderContext;
import ru.mipt.bit.platformer.interfaces.Renderable;
import ru.mipt.bit.platformer.objects.obstacles.ObstacleManager;
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
    private TextureRegion graphics;
    private Rectangle rectangle;

    private GridPoint2 pos;
    private float rotation;
    private float speed;

    Player(TiledMapTileLayer groundLayer, Texture texture, GridPoint2 pos) {
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        this.graphics = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(this.graphics);

        this.pos = pos;
        this.speed = 1f;
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

    private boolean isMoving() {
        return isEqual(this.speed, 1f);
    }

    private void updatePos(float deltaTime) {
        MoveContext moveCtx = getMoveContext();
        if (moveCtx == null)
            return;

        GridPoint2 newPos = new GridPoint2(this.pos).add(moveCtx.dx(), moveCtx.dy());
        if (this.isMoving()) {
            if (ObstacleManager.getInstance().contains(newPos)) {
                newPos.set(this.pos);
                this.speed = 0f;
            }
            this.rotation = moveCtx.rotation();
        }

        // calculate interpolated player screen coordinates
        this.tileMovement.moveRectangleBetweenTileCenters(this.rectangle, this.pos, newPos, this.speed);

        this.speed = continueProgress(this.speed, deltaTime, MOVEMENT_SPEED);
        if (this.isMoving()) {
            // record that the player has reached his/her destination
            this.pos.set(newPos);
        }
    }

    @Override
    public void render(RenderContext context) {
        this.updatePos(context.deltaTime);
        drawTextureRegionUnscaled(context.batch, this.graphics, this.rectangle, this.rotation);
    }
}
