package ru.mipt.bit.platformer;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;

import ru.mipt.bit.platformer.ai.RandomMovementStrategy;
import ru.mipt.bit.platformer.command.MoveCommand;
import ru.mipt.bit.platformer.level.FileLevelLoader;
import ru.mipt.bit.platformer.level.LevelLayout;
import ru.mipt.bit.platformer.level.LevelLoader;
import ru.mipt.bit.platformer.level.LayoutGeneration;
import ru.mipt.bit.platformer.manager.InputManager;
import ru.mipt.bit.platformer.model.Direction;
import ru.mipt.bit.platformer.model.Game;
import ru.mipt.bit.platformer.model.entity.Player;
import ru.mipt.bit.platformer.model.entity.Tree;
import ru.mipt.bit.platformer.view.Scene;
import ru.mipt.bit.platformer.view.sprite.PlayerSprite;
import ru.mipt.bit.platformer.view.sprite.TreeSprite;

import static com.badlogic.gdx.Input.Keys.A;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.DOWN;
import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.RIGHT;
import static com.badlogic.gdx.Input.Keys.S;
import static com.badlogic.gdx.Input.Keys.UP;
import static com.badlogic.gdx.Input.Keys.W;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameDesktopLauncher implements ApplicationListener {

    private static final String TANK_TEXTURE = "images/tank_blue.png";
    private static final String TREE_TEXTURE = "images/greenTree.png";
    private static final int AI_PLAYER_COUNT = 3;

    private Batch batch;

    private TiledMap level;
    private MapRenderer levelRenderer;

    private Game game;
    private Scene scene;
    private InputManager inputManager;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // load level tiles
        level = new TmxMapLoader().load("level.tmx");
        levelRenderer = createSingleLayerMapRenderer(level, batch);
        TiledMapTileLayer groundLayer = getSingleLayer(level);

        scene = new Scene();
        game = new Game(groundLayer.getWidth(), groundLayer.getHeight());

        // LevelLoader loader = new RandomLevelLoader(groundLayer.getWidth(), groundLayer.getHeight(), 5);
        LevelLoader loader = new FileLevelLoader("level.txt");
        LevelLayout layout = loader.load();

        Set<GridPoint2> occupied = new HashSet<>();

        addPlayer(layout.getPlayerPosition(), groundLayer);
        occupied.add(layout.getPlayerPosition());

        for (GridPoint2 treePosition : layout.getTreePositions()) {
            addTree(treePosition, groundLayer);
            occupied.add(treePosition);
        }

        LayoutGeneration positions = new LayoutGeneration(groundLayer.getWidth(), groundLayer.getHeight());
        for (GridPoint2 playerPosition : positions.pick(AI_PLAYER_COUNT, occupied)) {
            addAiPlayer(playerPosition, groundLayer);
        }

        initInputManager();
    }

    private void addPlayer(GridPoint2 position, TiledMapTileLayer groundLayer) {
        Player player = new Player(position);
        scene.add(new PlayerSprite(player, groundLayer, TANK_TEXTURE));
        game.addPlayer(player);
    }

    private void addAiPlayer(GridPoint2 position, TiledMapTileLayer groundLayer) {
        Player player = new Player(position);
        scene.add(new PlayerSprite(player, groundLayer, TANK_TEXTURE));
        game.addAiPlayer(player, new RandomMovementStrategy());
    }

    private void addTree(GridPoint2 position, TiledMapTileLayer groundLayer) {
        Tree tree = new Tree(position);
        scene.add(new TreeSprite(tree, groundLayer, TREE_TEXTURE));
        game.addObstacle(tree);
    }

    private void initInputManager() {
        Player player = game.getPlayer();
        inputManager = new InputManager(Gdx.input::isKeyPressed);
        inputManager.register(UP, new MoveCommand(player, Direction.UP, game));
        inputManager.register(W, new MoveCommand(player, Direction.UP, game));
        inputManager.register(LEFT, new MoveCommand(player, Direction.LEFT, game));
        inputManager.register(A, new MoveCommand(player, Direction.LEFT, game));
        inputManager.register(DOWN, new MoveCommand(player, Direction.DOWN, game));
        inputManager.register(S, new MoveCommand(player, Direction.DOWN, game));
        inputManager.register(RIGHT, new MoveCommand(player, Direction.RIGHT, game));
        inputManager.register(D, new MoveCommand(player, Direction.RIGHT, game));
    }

    @Override
    public void render() {
        // clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        inputManager.handleInput();
        game.update(Gdx.graphics.getDeltaTime());

        // render each tile of the level
        levelRenderer.render();

        scene.render(batch);
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        scene.dispose();
        level.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
