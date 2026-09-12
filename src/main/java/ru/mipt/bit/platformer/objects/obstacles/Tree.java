package ru.mipt.bit.platformer.objects.obstacles;

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
    private TextureRegion graphics;
    private Rectangle rectangle;
    private final GridPoint2 pos;

    public Tree(TiledMapTileLayer groundLayer, Texture texture, GridPoint2 pos) {
        this.graphics = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(this.graphics);
        this.pos = pos;
        moveRectangleAtTileCenter(groundLayer, this.rectangle, this.pos);
    }

    @Override
    public boolean contains(GridPoint2 point) {
        return this.pos == point;
    }

    @Override
    public void render(RenderContext context) {
        drawTextureRegionUnscaled(context.batch, this.graphics, this.rectangle, 0f);
    }
}
