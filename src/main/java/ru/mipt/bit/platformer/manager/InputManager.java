package ru.mipt.bit.platformer.manager;

import java.util.LinkedHashMap;
import java.util.Map;

import ru.mipt.bit.platformer.command.Runnable;

public class InputManager {

    private final KeyChecker keyChecker;
    private final Map<Integer, Runnable> handlers = new LinkedHashMap<>();

    public InputManager(KeyChecker keyChecker) {
        this.keyChecker = keyChecker;
    }

    public void register(int keycode, Runnable handler) {
        this.handlers.put(keycode, handler);
    }

    public void handleInput() {
        for (Map.Entry<Integer, Runnable> entry : this.handlers.entrySet()) {
            if (this.keyChecker.isPressed(entry.getKey())) {
                entry.getValue().run();
            }
        }
    }
}
