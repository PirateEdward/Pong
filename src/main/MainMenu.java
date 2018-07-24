package main;

import e.Game;
import e.Scene;
import e.Window;
import e.graphics.Shader;
import e.graphics.gui.Button;
import e.graphics.gui.Color;
import e.graphics.gui.Font;
import e.graphics.gui.Menu;
import e.math.Vector3f;
import g.entities.Background;
import g.entities.RandomBall;

public class MainMenu extends Menu {
	
	public static String[] text = new String[]{"Singleplayer", "Multiplayer", "Exit"};
	public static Font font = new Font("default_font", 2.0f, new Color(0.2f, 0.3f, 0.7f, 1.0f));
	
	public static Vector3f movedown = new Vector3f(0.0f, -2.5f, 0.0f);
	
	public Background background;

	public MainMenu() {
		super(text, font, new Vector3f(0.0f, 0.0f, 0.0f), new RandomBall(new Vector3f(0.0f, 0.0f, 0.0f)), 0.5f);
		background = new Background((RandomBall) entity);
	}
	
	public void update(Game game, Scene scene){
		super.update(game, scene);
	}
	
	@Override
	public void render(Shader shader, Vector3f position){
		super.render(shader, position);
		background.render();
	}

	@Override
	public void effect(Game game, Scene scene, Button source) {
		if(source == getButton(0)) {
			game.setCurrent(Pong.single_player);
		}
		else if(source == getButton(1)) {
			game.setCurrent(Pong.multi_player);
		}
		else if(source == getButton(2)) {
			System.out.println("Exiting...");
			Window.close();
		}
	}

}
