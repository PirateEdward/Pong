package e.entity;

import e.graphics.Model;
import e.graphics.Shader;
import e.graphics.VertexArrayRect;
import e.math.Matrix4f;
import e.math.Vector2f;
import e.math.Vector3f;

public class Entity {
	
	public float mass, width, height;
	protected Vector2f speed;
	public Vector3f position;
	public Model model;
	public Shader shader;

	public Entity(Vector3f position, Model model, Shader shader) {
		this.position = position;
		this.model = model;
		this.shader = shader;
		speed = new Vector2f();
		VertexArrayRect mesh = (VertexArrayRect) model.mesh;
		width = mesh.width;
		height = mesh.height;
		mass = 1.0f;
	}
	
	public void addForce(Vector2f force){
		speed.addTo(force.divBack(mass));
	}
	
	public void render(){
		shader.enable();
		setUniforms();
		model.render();
		shader.disable();
	}
	
	public void setUniforms(){
		shader.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
	}
	
	public void update(){
		position.addTo(speed);
		onUpdate();
	}
	
	public void onUpdate(){
		
	}
	
	public Vector2f getSpeed(){
		return speed;
	}
	
	public void setSpeed(Vector2f speed){
		this.speed = speed;
	}
	
	public float getRight(){
		return position.x + width / 2;
	}
	
	public float getLeft(){
		return position.x - width / 2;
	}
	
	public float getTop(){
		return position.y + height / 2;
	}
	
	public float getBottom(){
		return position.y - height / 2;
	}
	
	public boolean collision(Entity other){
		return collisionY(other) && collisionX(other);
	}
	
	public boolean collisionY(Entity other){
		return getTop() > other.getBottom() && other.getTop() > getBottom();
	}
	
	public boolean collisionX(Entity other){
		return getRight() > other.getLeft() && other.getRight() > getLeft();
	}
	

}
