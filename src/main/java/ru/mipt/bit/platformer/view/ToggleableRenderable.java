package ru.mipt.bit.platformer.view;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Layer: Interface Adapter. Role: Output Adapter.
 */
public class ToggleableRenderable implements Renderable, Toggleable {

    private final Renderable component;
    private boolean visible;

    public ToggleableRenderable(Renderable component) {
        this.component = component;
    }

    @Override
    public void render(Batch batch) {
        if (this.visible) {
            this.component.render(batch);
        }
    }

    @Override
    public void dispose() {
        this.component.dispose();
    }

    @Override
    public void toggle() {
        this.visible = !this.visible;
    }
}
