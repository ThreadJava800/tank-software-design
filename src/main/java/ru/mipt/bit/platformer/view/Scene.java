package ru.mipt.bit.platformer.view;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Scene {

    private final List<Renderable> objects = new ArrayList<>();

    public void add(Renderable renderable) {
        this.objects.add(renderable);
    }

    public void render(Batch batch) {
        batch.begin();
        for (Renderable renderable : this.objects) {
            renderable.render(batch);
        }
        batch.end();
    }

    public void dispose() {
        for (Renderable renderable : this.objects) {
            renderable.dispose();
        }
    }
}
