package main;

import static org.lwjgl.glfw.GLFW.*;
import e.Game;
import e.Resources;
import e.Scene;
import e.graphics.Shader;
import e.graphics.gui.Color;
import e.graphics.gui.Font;
import e.graphics.gui.Text;
import e.math.Vector3f;

public class MenuScene extends Scene {
	
	public Text pong;
	public Shader textShader;
	public MainMenu main;

	public MenuScene(int id) {
		super(id);
		
		textShader = Resources.getShader("text");
		pong = new Text("PONG", new Font("default_font", 4.0f, new Color(0.7f, 0.3f, 0.1f, 1.0f)));
		
		main = new MainMenu();
	}

	@Override
	public void update(Game game) {
		main.update(game, this);
	}

	@Override
	public void render() {
		pong.render(textShader, new Vector3f(0.0f, 5.0f, 0.0f));
		main.render(textShader, new Vector3f());
	}

	@Override
	public void onSelect() {
		
	}

	@Override
	public void isKeyReleased(Game game, int key, int action) {
		if(action == GLFW_PRESS){
			if(key == GLFW_KEY_UP)
				main.moveUp();
			if(key == GLFW_KEY_DOWN)
				main.moveDown();
		}
		else if(action == GLFW_RELEASE){
			if(key == GLFW_KEY_ENTER)
				main.buttons.getSelected().effect(game, this);
		}
	}

}
