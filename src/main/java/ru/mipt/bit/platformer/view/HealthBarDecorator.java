package ru.mipt.bit.platformer.view;

import com.badlogic.gdx.graphics.g2d.Batch;

public class HealthBarDecorator implements Renderable {

    private final Renderable component;
    private final Renderable healthBar;

    public HealthBarDecorator(Renderable component, Renderable healthBar) {
        this.component = component;
        this.healthBar = healthBar;
    }

    @Override
    public void render(Batch batch) {
        this.component.render(batch);
        this.healthBar.render(batch);
    }

    @Override
    public void dispose() {
        this.component.dispose();
        this.healthBar.dispose();
    }
}
