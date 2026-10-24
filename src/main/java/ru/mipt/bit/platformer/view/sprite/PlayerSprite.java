package ru.mipt.bit.platformer.view.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;

import ru.mipt.bit.platformer.view.Renderable;
import ru.mipt.bit.platformer.model.entity.Player;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class PlayerSprite implements Renderable {

    private final Player player;
    private final TileMovement tileMovement;
    private final Texture texture;
    private final TextureRegion graphics;
    private final Rectangle rectangle;

    public PlayerSprite(Player player, TiledMapTileLayer groundLayer, String texturePath) {
        this.player = player;
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        this.texture = new Texture(texturePath);
        this.graphics = new TextureRegion(this.texture);
        this.rectangle = createBoundingRectangle(this.graphics);
    }

    @Override
    public void render(Batch batch) {
        this.tileMovement.moveRectangleBetweenTileCenters(this.rectangle, this.player.getCurrPos(), this.player.getNextPos(), this.player.getProgress());
        drawTextureRegionUnscaled(batch, this.graphics, this.rectangle, this.player.getRotation());
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }
}
