package e.math;

public class Vector2f {
	
	public float x, y;

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2f(){
		this(0.0f, 0.0f);
	}
	
	public Vector2f addBack(Vector2f v){
		return new Vector2f(x + v.x, y + v.y);
	}
	
	public Vector2f invertBack(){
		return new Vector2f(-x, -y);
	}
	
	public Vector2f subBack(Vector2f v){
		return addBack(v.invertBack());
	}
	
	public Vector2f mulBack(float multiplier){
		return new Vector2f(x * multiplier, y * multiplier);
	}
	
	public Vector2f divBack(float divider){
		return mulBack(1 / divider);
	}
	
	public void addTo(Vector2f v){
		x += v.x;
		y += v.y;
	}
	
	public void subTo(Vector2f v){
		x -= v.x;
		y -= v.y;
	}
	
	public void invertTo(){
		x = -x;
		y = -y;
	}
	
	public void mulTo(float multiplier){
		x *= multiplier;
		y *= multiplier;
	}
	
	public void divTo(float divider){
		x /= divider;
		y /= divider;
	}
	
	public boolean equals(Vector2f other){
		return x == other.x &&  y == other.y;
	}
	
}