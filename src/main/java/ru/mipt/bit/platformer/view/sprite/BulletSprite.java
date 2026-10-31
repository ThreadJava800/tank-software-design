package ru.mipt.bit.platformer.view.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;

import ru.mipt.bit.platformer.view.Renderable;
import ru.mipt.bit.platformer.model.entity.Bullet;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class BulletSprite implements Renderable {

    private final Bullet bullet;
    private final TileMovement tileMovement;
    private final Texture texture;
    private final TextureRegion graphics;
    private final Rectangle rectangle;

    public BulletSprite(Bullet bullet, TiledMapTileLayer groundLayer, String texturePath) {
        this.bullet = bullet;
        this.tileMovement = new TileMovement(groundLayer, Interpolation.linear);
        this.texture = new Texture(texturePath);
        this.graphics = new TextureRegion(this.texture);
        this.rectangle = createBoundingRectangle(this.graphics);
    }

    @Override
    public void render(Batch batch) {
        this.tileMovement.moveRectangleBetweenTileCenters(this.rectangle, this.bullet.getCurrPos(), this.bullet.getNextPos(), this.bullet.getProgress());
        drawTextureRegionUnscaled(batch, this.graphics, this.rectangle, this.bullet.getRotation());
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }
}
