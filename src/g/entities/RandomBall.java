package g.entities;

import e.Resources;
import e.Window;
import e.entity.Entity;
import e.graphics.Model;
import e.graphics.VertexArrayRect;
import e.math.Vector2f;
import e.math.Vector3f;

public class RandomBall extends Entity {
	
	public static float ball_speed = 0.5f;
	
	public float x = 0.01f, y = 0.01f, z = -0.015f;
	
	public Vector3f color;
	public Entity left, right;

	public RandomBall(Vector3f position) {
		super(position, new Model(new VertexArrayRect(1.0f, 1.0f, 0.0f)), Resources.getShader("random_ball"));
		speed.addTo(new Vector2f(ball_speed, 0.0f));
		color = new Vector3f(0.3f, 0.4f, 0.5f);
		color.x = Math.abs(color.x);
		color.y = Math.abs(color.y);
	}
	
	public void setEntityCollision(Entity left, Entity right){
		this.left = left;
		this.right = right;
	}
	
	@Override
	public void setUniforms(){
		super.setUniforms();
		shader.setUniform3f("c", color);
	}
	
	@Override
	public void onUpdate() {
		if(left != null && right != null){
			if((position.y > Window.top - height / 2 - 1.0f && speed.y > 0) || (position.y < Window.bottom + height / 2 && speed.y < 0)) {
				speed.y = -speed.y;
			}
		
			if(collision(left)) {
				float yDistance = left.position.y - position.y;
				float angle = getAngle(yDistance);
				speed = new Vector2f((float) Math.cos(angle) * ball_speed, (float) -Math.sin(angle) * ball_speed);
			}
		
			if(collision(right)) {
				float yDistance = right.position.y - position.y;
				float angle = getAngle(yDistance);
				speed = new Vector2f((float) -Math.cos(angle) * ball_speed, (float) Math.sin(angle) * ball_speed);
			}
		}
		
		color.x += x;
		color.y += y;
		color.z += z;
		if(color.x >= 0.8f || color.x <= 0.2f)
			x = -x;
		if(color.y >= 0.8f || color.y <= 0.2f)
			y = -y;
		if(color.z >= 0.8f || color.z <= 0.2f)
			z = -z;
		
		//if(getRight() < left.getLeft() || getLeft() > right.getRight())
			//reset();
	}
	
	public float getAngle(float yDistance){
		if(yDistance > height) yDistance -= height / 2;
		else if(yDistance < -height) yDistance += height / 2;
		float angleMultiplier = yDistance / (right.height / 2);
		if(angleMultiplier > 1.0f) angleMultiplier = 0.9f;
		return (float) (Math.PI / 3) * angleMultiplier;
	}
	
	public void reset(){
		position.x = position.y = 0.0f;
		speed = new Vector2f(ball_speed * (speed.x < 0 ? 1 : -1), 0);
	}

}
