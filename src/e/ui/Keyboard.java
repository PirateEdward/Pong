package e.ui;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import e.Game;

public class Keyboard extends GLFWKeyCallback {
	
	public static boolean[] keys;
	
	public Game game;

	public Keyboard(Game game) {
		keys = new boolean[65536];
		this.game = game;
	}

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		keyAction(key, action);
		keys[key] = (action != GLFW.GLFW_RELEASE);
	}
	
	public void keyAction(int key, int action) {
		game.getCurrent().isKeyReleased(game, key, action);
	}
	
	public static boolean isKeyDown(int keycode) {
	    return keys[keycode];
	}

}
