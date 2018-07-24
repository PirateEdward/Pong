package e.math;

import e.Resources;

public class Vector3f {
	
	public float x, y, z;
	
	public Vector3f(Vector2f v){
		this(v, 0.0f);
	}
	
	public Vector3f(Vector2f v, float z){
		this(v.x, v.y, z);
	}
	
	public Vector3f(float z){
		this.z = z;
		this.x = Resources.rand.nextFloat() * ((Resources.rand.nextInt(2) == 0) ? 1 : -1);
		this.y = Resources.rand.nextFloat() * ((Resources.rand.nextInt(2) == 0) ? 1 : -1);
	}

	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f(){
		this(0.0f, 0.0f, 0.0f);
	}
	
	public Vector3f addBack(Vector3f other){
		return new Vector3f(x + other.x, y + other.y, z + other.z);
	}
	
	public Vector3f subBack(Vector3f other){
		return addBack(other.invertBack());
	}
	
	public Vector3f invertBack(){
		return new Vector3f(-x, -y, -z);
	}
	
	public Vector3f mulBack(float mul){
		return new Vector3f(x * mul, y * mul, z);
	}
	
	public Vector3f divBack(float div){
		return new Vector3f(x / div, y / div, z);
	}
	
	public void addTo(Vector2f other){
		x += other.x;
		y += other.y;
	}
	
	public void subTo(Vector2f other){
		x -= other.x;
		y -= other.y;
	}
	
	public void addTo(Vector3f other){
		x += other.x;
		y += other.y;
		z += other.z;
	}
	
	public void subTo(Vector3f other){
		addTo(other.invertBack());
	}
	
	public void mulTo(float mul){
		x *= mul;
		y *= mul;
		z *= mul;
	}
	
	public void divTo(float div){
		mulTo(1 / div);
	}

}
