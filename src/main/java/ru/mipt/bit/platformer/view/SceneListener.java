package ru.mipt.bit.platformer.view;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import ru.mipt.bit.platformer.model.LevelListener;
import ru.mipt.bit.platformer.model.entity.Bullet;
import ru.mipt.bit.platformer.model.entity.Player;
import ru.mipt.bit.platformer.model.entity.Tree;
import ru.mipt.bit.platformer.view.sprite.BulletSprite;
import ru.mipt.bit.platformer.view.sprite.PlayerSprite;
import ru.mipt.bit.platformer.view.sprite.TreeSprite;

public class SceneListener implements LevelListener {

    private static final String TANK_TEXTURE = "images/tank_blue.png";
    private static final String TREE_TEXTURE = "images/greenTree.png";
    private static final String BULLET_TEXTURE = "images/bullet.png";

    private final Scene scene;
    private final TiledMapTileLayer groundLayer;
    private final ToggleGroup healthBars;
    private final Map<Object, Renderable> sprites = new HashMap<>();

    public SceneListener(Scene scene, TiledMapTileLayer groundLayer, ToggleGroup healthBars) {
        this.scene = scene;
        this.groundLayer = groundLayer;
        this.healthBars = healthBars;
    }

    @Override
    public void onAdded(Object object) {
        Renderable sprite = this.createSprite(object);
        this.sprites.put(object, sprite);
        this.scene.add(sprite);
    }

    @Override
    public void onRemoved(Object object) {
        Renderable sprite = this.sprites.remove(object);
        this.healthBars.remove(object);
        this.scene.remove(sprite);
        sprite.dispose();
    }

    private Renderable createSprite(Object object) {
        if (object instanceof Player) {
            Player player = (Player) object;
            ToggleableRenderable healthBar = new ToggleableRenderable(new HealthBar(player, this.groundLayer));
            this.healthBars.add(player, healthBar);
            return new HealthBarDecorator(new PlayerSprite(player, this.groundLayer, TANK_TEXTURE), healthBar);
        }
        if (object instanceof Tree) {
            return new TreeSprite((Tree) object, this.groundLayer, TREE_TEXTURE);
        }
        return new BulletSprite((Bullet) object, this.groundLayer, BULLET_TEXTURE);
    }
}
