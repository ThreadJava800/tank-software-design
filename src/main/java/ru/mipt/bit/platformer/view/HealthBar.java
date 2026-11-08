package ru.mipt.bit.platformer.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;

import ru.mipt.bit.platformer.model.entity.Player;
import ru.mipt.bit.platformer.util.TileMovement;

public class HealthBar implements Renderable {

    private static final float BAR_HEIGHT = 12f;

    private final Player player;
    private final TileMovement tileMovement;
    private final Rectangle rectangle;
    private final Texture texture;

    public HealthBar(Player player, TiledMapTileLayer groundLayer) {
        this.player = player;
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        this.rectangle = new Rectangle().setWidth(groundLayer.getTileWidth()).setHeight(groundLayer.getTileHeight());
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        this.texture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void render(Batch batch) {
        this.tileMovement.moveRectangleBetweenTileCenters(this.rectangle, this.player.getCurrPos(), this.player.getNextPos(), this.player.getProgress());
        float y = this.rectangle.y + this.rectangle.height - BAR_HEIGHT;
        batch.setColor(Color.RED);
        batch.draw(this.texture, this.rectangle.x, y, this.rectangle.width, BAR_HEIGHT);
        batch.setColor(Color.GREEN);
        batch.draw(this.texture, this.rectangle.x, y, this.rectangle.width * this.player.getHealthRatio(), BAR_HEIGHT);
        batch.setColor(Color.WHITE);
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }
}
