package g.entities;

import e.Resources;
import e.Window;
import e.entity.Entity;
import e.graphics.Model;
import e.graphics.VertexArrayRect;
import e.math.Vector2f;
import e.math.Vector3f;

public class Bar extends Entity {
	
	public static Vector2f max_speed = new Vector2f(0.0f, 0.3f);
	
	public boolean up, down;

	public Bar(Vector3f position) {
		super(position, new Model(new VertexArrayRect(0.5f, 4.0f, 0.0f)), Resources.getShader("object"));
		position.x += width / 2;
	}
	
	public void onUpdate(){
		if(up && getTop() < Window.top - 1.0f)
			speed = max_speed;
		else if(down && getBottom() > Window.bottom)
			speed = max_speed.invertBack();
		else
			speed = new Vector2f();
	}

}
