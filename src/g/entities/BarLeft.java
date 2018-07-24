package g.entities;

import org.lwjgl.glfw.GLFW;

import e.math.Vector3f;
import e.ui.Keyboard;

public class BarLeft extends Bar {

	public BarLeft(Vector3f position) {
		super(position);
	}
	
	public void onUpdate(){
		up = Keyboard.isKeyDown(GLFW.GLFW_KEY_W);
		down = Keyboard.isKeyDown(GLFW.GLFW_KEY_S);
		super.onUpdate();
	}

}
