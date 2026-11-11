package ru.mipt.bit.platformer.command;

import ru.mipt.bit.platformer.view.Toggleable;

/**
 * Layer: Application. Role: Use Case.
 */
public class ToggleHealthBarCommand implements Runnable {

    private final Toggleable healthBars;

    public ToggleHealthBarCommand(Toggleable healthBars) {
        this.healthBars = healthBars;
    }

    @Override
    public void run() {
        this.healthBars.toggle();
    }
}
