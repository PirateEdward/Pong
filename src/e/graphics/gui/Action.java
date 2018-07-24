package e.graphics.gui;

import e.Game;
import e.Scene;

public interface Action {
	
	public abstract void effect(Game game, Scene scene, Button source);

}
