package e.graphics.gui;

import e.Game;
import e.Scene;
import e.graphics.Shader;
import e.math.Vector3f;

public class Button {
	
	public Text text;
	public Action action;
	public Vector3f position;
	
	public Button(Text text, Vector3f position){
		this.text = text;
		action = new Action() {
			@Override
			public void effect(Game game, Scene scene, Button source) {
				
			}
		};
		this.position = position;
	}

	public Button(Text text, Vector3f position, Action action) {
		this.text = text;
		this.action = action;
		this.position = position;
	}
	
	public void setAction(Action action){
		this.action = action;
	}
	
	public void render(Shader shader){
		text.render(shader, position);
	}
	
	public void effect(Game game, Scene scene){
		action.effect(game, scene, this);
	}
	
	public Vector3f getLeft(){
		return position.addBack(new Vector3f((-text.text.length() / 2 - 1) * text.font.size, 0.0f, 0.0f));
	}

}
