package e;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;

public class Monitor {
	
	public static int Width, Height;
	private static GLFWVidMode vidmode;
	
	public static void init(){
		vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		Width = vidmode.width();
		Height = vidmode.height();
	}

}
