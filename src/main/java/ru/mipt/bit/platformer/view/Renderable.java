package ru.mipt.bit.platformer.view;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Layer: Interface Adapter. Role: Output Port.
 */
public interface Renderable {
    void render(Batch batch);
    void dispose();
}
