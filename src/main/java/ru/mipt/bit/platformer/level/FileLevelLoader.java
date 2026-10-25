package ru.mipt.bit.platformer.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.GridPoint2;

public class FileLevelLoader implements LevelLoader {

    private static final char TREE_SYM = 'T';
    private static final char PLAYER_SYM = 'X';

    private final String path;

    public FileLevelLoader(String path) {
        this.path = path;
    }

    @Override
    public LevelLayout load() {
        List<String> rows = this.readRows();

        GridPoint2 playerPosition = null;
        List<GridPoint2> treePositions = new ArrayList<>();

        for (int row = 0; row < rows.size(); row++) {
            String line = rows.get(row);
            int y = rows.size() - 1 - row;
            for (int x = 0; x < line.length(); x++) {
                char symbol = line.charAt(x);
                if (symbol == TREE_SYM) {
                    treePositions.add(new GridPoint2(x, y));
                } else if (symbol == PLAYER_SYM) {
                    playerPosition = new GridPoint2(x, y);
                }
            }
        }

        return new LevelLayout(playerPosition, treePositions);
    }

    private List<String> readRows() {
        List<String> rows = new ArrayList<>();
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(this.path);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                rows.add(line);
            }
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
        return rows;
    }
}
