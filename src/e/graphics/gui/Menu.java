package e.graphics.gui;

import e.Game;
import e.Scene;
import e.entity.Entity;
import e.graphics.Shader;
import e.math.Vector2f;
import e.math.Vector3f;

public abstract class Menu implements Action {
	
	public ButtonGroup buttons;
	public Entity entity;
	public Vector3f movedown;
	
	public Menu(String[] text, Font font, Vector3f top, Entity entity, float spacing) {
		Button[] buttons = new Button[text.length];
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new Button(new Text(text[i], font), top.addBack(new Vector3f(0.0f, (i == 0 ? 0 : font.size) * -i -i * spacing, 0.0f)), this);
		}
		this.buttons = new ButtonGroup(buttons);
		this.entity = entity;
		this.entity.setSpeed(new Vector2f());
		movedown = new Vector3f(0.0f, -(font.size + spacing), 0.0f);
		this.entity.position = movedown.mulBack((this.buttons.selected - 1) / 2).addBack(this.buttons.getSelected().getLeft());
	}
	
	public void moveDown(){
		buttons.incrementSelected();
		entity.position = movedown.mulBack((buttons.selected - 1) / 2).addBack(buttons.getSelected().getLeft());
	}
	
	public void moveUp(){
		buttons.decrementSelected();
		entity.position = movedown.mulBack((buttons.selected - 1) / 2).addBack(buttons.getSelected().getLeft());
	}
	
	public Button getButton(int id){
		return buttons.get(id);
	}
	
	public void update(Game game, Scene scene){
		entity.update();
	}
	
	public void render(Shader shader, Vector3f top){
		for(int i = 0; i < buttons.getSize(); i++){
			buttons.get(i).render(shader);
		}
		entity.render();
	}

}
