package g.entities;

import e.Resources;
import e.Window;
import e.entity.Entity;
import e.graphics.Model;
import e.graphics.VertexArrayRect;
import e.graphics.gui.Color;
import e.math.Vector3f;

public class MiddleLine extends Entity {
	
	public static Color color = new Color(0.5f, 0.2f, 0.6f, 0.5f);

	public MiddleLine(Vector3f position) {
		super(position, new Model(new VertexArrayRect(0.2f, Window.Width, 0.0f)), Resources.getShader("color"));
	}
	
	@Override
	public void setUniforms(){
		super.setUniforms();
		shader.setColorUniform("color_in", color);
	}
}
