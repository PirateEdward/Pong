package main;

import e.Game;
import e.Resources;
import e.Scene;
import e.Window;
import e.ui.Keyboard;

public class Pong extends Game {
	
	public static final int single_player = 0, multi_player = 1,  menu = 2;

	public static void main(String[] args) {
		new Pong().start();
	}
	
	public static Scene[] getScenes() {
		Scene[] scenes = new Scene[3];
		scenes[0] = new GameScene(single_player, true);
		scenes[1] = new GameScene(multi_player, false);
		scenes[2] = new MenuScene(menu);
		return scenes;
	}
	
	public void init(){
		createWindow(1120, 630, "Pong");
		Window.setKeyCallBack(keyCallBack = new Keyboard(this));
		Window.setLocationCenter();
		Window.setProjectionMatrix(-16, 16, -9, 9, -1, 1);
		
		initOpenGL();
		
		Window.setClearColor(0.15f, 0.15f, 0.3f, 1.0f);
		
		/// loading
		new Resources();
		
		setScenes(getScenes());
		setCurrent(Pong.menu);
	}

}
