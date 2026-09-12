package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import ru.mipt.bit.platformer.interfaces.Obstacle;
import ru.mipt.bit.platformer.interfaces.RenderContext;
import ru.mipt.bit.platformer.interfaces.Renderable;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tree implements Obstacle, Renderable {

    private Texture texture;
    private TextureRegion graphics;
    private Rectangle rectangle;
    private final GridPoint2 pos;

    public Tree(TiledMapTileLayer groundLayer, GridPoint2 pos) {
        this.texture = new Texture("images/greenTree.png");
        this.graphics = new TextureRegion(this.texture);
        this.rectangle = createBoundingRectangle(this.graphics);
        this.pos = new GridPoint2(pos);
        moveRectangleAtTileCenter(groundLayer, this.rectangle, this.pos);
    }

    @Override
    public boolean contains(GridPoint2 point) {
        return this.pos.equals(point);
    }

    @Override
    public void render(RenderContext context) {
        drawTextureRegionUnscaled(context.batch, this.graphics, this.rectangle, 0f);
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }
}
