package g.entities;

import org.lwjgl.glfw.GLFW;

import e.math.Vector3f;
import e.ui.Keyboard;

public class BarRight extends Bar {

	public BarRight(Vector3f position) {
		super(position);
	}
	
	public void onUpdate(){
		up = Keyboard.isKeyDown(GLFW.GLFW_KEY_UP);
		down = Keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN);
		super.onUpdate();
	}

}
