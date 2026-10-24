package ru.mipt.bit.platformer.view.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

import ru.mipt.bit.platformer.view.Renderable;
import ru.mipt.bit.platformer.model.entity.Tree;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;
import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

public class TreeSprite implements Renderable {

    private final Texture texture;
    private final TextureRegion graphics;
    private final Rectangle rectangle;

    public TreeSprite(Tree tree, TiledMapTileLayer groundLayer, String texturePath) {
        this.texture = new Texture(texturePath);
        this.graphics = new TextureRegion(this.texture);
        this.rectangle = createBoundingRectangle(this.graphics);
        moveRectangleAtTileCenter(groundLayer, this.rectangle, tree.getPos());
    }

    @Override
    public void render(Batch batch) {
        drawTextureRegionUnscaled(batch, this.graphics, this.rectangle, 0f);
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }
}
