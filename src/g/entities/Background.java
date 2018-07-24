package g.entities;

import e.Resources;
import e.Window;
import e.entity.Entity;
import e.graphics.Model;
import e.graphics.VertexArrayRect;
import e.math.Vector3f;

public class Background extends Entity {
	
	public RandomBall ball;

	public Background(RandomBall ball) {
		super(new Vector3f(0.0f, 0.0f, -0.1f), new Model(new VertexArrayRect(Window.Width, Window.Height, 0.0f)), Resources.getShader("background"));
		this.ball = ball;
	}
	
	@Override
	public void setUniforms(){
		super.setUniforms();
		shader.setUniform2f("ball", ball.position.x, ball.position.y);
		shader.setUniform3f("ball_color", ball.color);
	}

}
