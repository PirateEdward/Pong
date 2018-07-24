package e;

public abstract class Scene {
	
	private int id;

	public Scene(int id) {
		this.id = id;
	}
	
	public abstract void update(Game game);
	
	public abstract void render();
	
	public abstract void onSelect();
	
	public abstract void isKeyReleased(Game game, int key, int action);
	
	public int getID(){
		return id;
	}

}
