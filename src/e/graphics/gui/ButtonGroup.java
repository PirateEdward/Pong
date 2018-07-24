package e.graphics.gui;

public class ButtonGroup {
	
	private Button[] buttons;
	public int selected;

	public ButtonGroup(Button[] buttons) {
		this.buttons = buttons;
	}
	
	public Button get(int id){
		if(id < 0 || id > buttons.length - 1)
			return null;
		return buttons[id];
	}
	
	public void setSelected(int id){
		if(id < 0 || id > buttons.length - 1)
			return;
		selected = id;
	}
	
	public void incrementSelected(){
		selected++;
		if(selected == buttons.length){
			setSelected(0);
		}
	}
	
	public void decrementSelected(){
		selected--;
		if(selected == -1){
			setSelected(buttons.length - 1);
		}
	}
	
	public Button getSelected(){
		return buttons[selected];
	}
	
	public int getSize(){
		return buttons.length;
	}

}
