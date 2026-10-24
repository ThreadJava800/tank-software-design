package ru.mipt.bit.platformer.level;

import java.util.List;

import com.badlogic.gdx.math.GridPoint2;

public class LevelLayout {

    private final GridPoint2 playerPosition;
    private final List<GridPoint2> treePositions;

    public LevelLayout(GridPoint2 playerPosition, List<GridPoint2> treePositions) {
        this.playerPosition = playerPosition;
        this.treePositions = treePositions;
    }

    public GridPoint2 getPlayerPosition() {
        return this.playerPosition;
    }

    public List<GridPoint2> getTreePositions() {
        return this.treePositions;
    }
}
