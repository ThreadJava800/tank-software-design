package ru.mipt.bit.platformer;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.interfaces.RenderContext;
import ru.mipt.bit.platformer.interfaces.Renderable;
import ru.mipt.bit.platformer.objects.Player;
import ru.mipt.bit.platformer.objects.Tree;

import ru.mipt.bit.platformer.ObstacleManager;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Scene implements Renderable {

    private static final GridPoint2 PLAYER_POS = new GridPoint2(1, 1);
    private static final GridPoint2 TREE_POS = new GridPoint2(1, 3);

    private ArrayList<Renderable> objects = new ArrayList<Renderable>();

    private void createPlayer(TiledMapTileLayer groundLayer, GridPoint2 pos) {
        Player player = new Player(groundLayer, pos);
        this.objects.add(player);
    }

    private void createTree(TiledMapTileLayer groundLayer, GridPoint2 pos) {
        Tree tree = new Tree(groundLayer, pos);
        ObstacleManager.getInstance().addObstacle(tree);
        this.objects.add(tree);
    }

    public void build(TiledMap level) {
        TiledMapTileLayer groundLayer = getSingleLayer(level);
        this.createPlayer(groundLayer, PLAYER_POS);
        this.createTree(groundLayer, TREE_POS);
    }

    @Override
    public void render(RenderContext context) {
        context.batch.begin();

        for (Renderable renderable : this.objects) {
            renderable.render(context);
        }

        context.batch.end();
    }

    @Override
    public void dispose() {
        for (Renderable renderable : this.objects) {
            renderable.dispose();
        }
    }

}
