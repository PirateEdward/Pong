package e;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWKeyCallbackI;

import e.math.Matrix4f;

public abstract class Window {
	
	public static long id;
	
	public static float left, right, bottom, top, near, far;
	public static int Width, Height;
	public static Matrix4f pr_matrix;
	
	public static void create(int w, int h, String title) {
		if(id != 0) return;
		Width = w;
		Height = h;
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		id = glfwCreateWindow(Width, Height, title, 0, 0);
		glfwMakeContextCurrent(id);
		glfwShowWindow(id);
	}
	
	public static void enableVSync(){
		glfwSwapInterval(1);
	}
	
	public static void setProjectionMatrix(float left, float right, float bottom, float top, float near, float far) {
		Window.left = left;
		Window.right = right;
		Window.bottom = bottom;
		Window.top = top;
		Window.near = near;
		Window.far = far;
		pr_matrix = Matrix4f.orthographic(left, right, bottom, top, near, far);
	}
	
	public static void setKeyCallBack(GLFWKeyCallbackI callBack){
		glfwSetKeyCallback(id, callBack);
	}
	
	public static boolean shouldClose(){
		return glfwWindowShouldClose(id);
	}
	
	public static void setLocationCenter(){
		glfwSetWindowPos(id, (Monitor.Width - Width) / 2, (Monitor.Height - Height) / 2);
	}
	
	public static void update(){
		glfwPollEvents();
	}
	
	public static void clear(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public static void refresh(){
		glfwSwapBuffers(id);
	}
	
	public static void close(){
		glfwSetWindowShouldClose(id, true);
	}
	
	public static void destroy(){
		glfwDestroyWindow(Window.id);
		glfwTerminate();
	}
	
	public static void setClearColor(float r, float g, float b, float a){
		glClearColor(0.15f, 0.15f, 0.3f, 0.0f);
	}

}
