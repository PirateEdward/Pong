package g.entities;

import e.Resources;
import e.Window;
import e.entity.Entity;
import e.graphics.Model;
import e.graphics.VertexArrayRect;
import e.math.Vector3f;

public class Foreground extends Entity {

	public Foreground(Vector3f position) {
		super(position, new Model(new VertexArrayRect(Window.Width, Window.Height, 0.0f)), Resources.getShader("foreground"));
	}

}
