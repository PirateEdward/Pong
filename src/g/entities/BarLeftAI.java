package g.entities;

import e.math.Vector2f;
import e.math.Vector3f;

public class BarLeftAI extends Bar {
	
	public RandomBall ball;

	public BarLeftAI(Vector3f position, RandomBall ball) {
		super(position);
		this.ball = ball;
	}
	
	@Override
	public void onUpdate(){
		if(Math.abs(ball.position.y - position.y) > height / 2) {
			if(ball.position.y - getBottom() < 0) {
				speed = new Vector2f(0.0f, -0.3f);
			}
			else if(ball.position.y - getTop() > 0) {
				speed = new Vector2f(0.0f, 0.3f);
			}
		}
		else {
			speed = new Vector2f();
		}
		//super.onUpdate();
	}

}
