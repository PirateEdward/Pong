package e;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;

public abstract class Game implements Runnable {
	
	public Thread thread;
	public boolean running;
	public GLFWKeyCallback keyCallBack;
	
	private Scene[] scenes;
	private int currentScene;
	
	public void setCurrent(int newScene){
		if(newScene < 0 || newScene > scenes.length - 1)
			return;
		currentScene = newScene;
		scenes[currentScene].onSelect();
	}
	
	public Scene getCurrent(){
		return scenes[currentScene];
	}
	
	public void setScenes(Scene[] scenes){
		this.scenes = scenes;
		currentScene = 0;
	}
	
	public void start(){
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	@Override
	public final void run(){
		init();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1000000000.0 / 60.0;
		double delta = 0.0;
		int updates = 0, frames = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1.0){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
			
			if(Window.shouldClose())
				running = false;
		}
		Window.destroy();
		System.out.println("done");
	}
	
	public final void createWindow(int Width, int Height, String title){
		if(!glfwInit())
			System.out.println("Init Error");
		Monitor.init();
		Window.create(Width, Height, title);
		Window.enableVSync();
	}
	
	public final void initOpenGL(){
		GL.createCapabilities();
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glActiveTexture(GL_TEXTURE1);
		System.out.println("OpenGL : " + glGetString(GL_VERSION));
	}
	
	public abstract void init();
	
	public final void update(){
		Window.update();
		scenes[currentScene].update(this);
	}
	
	public final void render(){
		Window.clear();
		scenes[currentScene].render();
		Window.refresh();
	}

}
