package asteroids.input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

public class KeyboardInput {

    private final Set<KeyCode> pressedKeys = new HashSet<>();

    public KeyboardInput(Scene scene) {
        scene.setOnKeyPressed(e -> pressedKeys.add(e.getCode()));
        scene.setOnKeyReleased(e -> pressedKeys.remove(e.getCode()));
    }

    public boolean isPressed(KeyCode key) {
        return pressedKeys.contains(key);
    }
}
